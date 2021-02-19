Imports System.IO
Imports System.Text
Imports System.Xml
Imports Newtonsoft.Json
Imports System.Configuration

Public Class HMCTSPerformanceUtility
    Dim iFirst As Integer = 401
    Dim iLast As Integer = 401
    Dim iMachines As Integer = 0
    Dim iMaxNoOfUsers As Integer = 1
    Dim passed As New Dictionary(Of String, List(Of String))
    Dim failed As New Dictionary(Of String, List(Of String))
    Dim loading As Boolean = False
    Private Const EmailDomain As String = "@hearings.reform.hmcts.net"
    Private Const Judge As String = "TPClerk"
    Private Const UserPrefix As String = "TP"
    Private ApiUrl As String
    Private TenantId As String
    Private ClientId As String
    Private ClientSecret As String
    Dim participantTypes As Hashtable = New Hashtable()
    Dim participantCaseHearingRole As Hashtable = New Hashtable()
    Dim participants As List(Of String) = New List(Of String)
    Private Const CreateHearingJmxFile As String = "BP01 Create Hearings.jmx"

    Private Sub Bgo_Click(sender As Object, e As EventArgs) Handles Bgo.Click
        Bgo.Visible = False
        Dim Da As String = TBdate.Text.Trim & ","
        Dim Ti As String = TBtime.Text.Trim & ","

        Dim hList As New List(Of String)
        Dim judgeList As Hashtable = New Hashtable()
        Dim participantList As Hashtable = New Hashtable()

        Dim iAllocate As Integer = 0
        For i As Integer = iFirst To iLast
            iAllocate += 1
            Dim Ps As String = (i.ToString).PadLeft(4, "0") & ","
            Dim Hs As String = (i.ToString).PadLeft(4, "0")
            Dim oP As String = Ps & Da & Ti & "15," & Ps

            For k As Integer = 1 To iMaxNoOfUsers
                If k <> 1 Then
                    oP &= ","
                End If
                oP &= "_" & k
            Next

            hList.Add(oP)

            Dim tempJudgeList As New List(Of String)
            Dim tempParticipantList As New List(Of String)

            If judgeList.ContainsKey(iAllocate) Then
                tempJudgeList = judgeList(iAllocate)
            End If

            If participantList.ContainsKey(iAllocate) Then
                tempParticipantList = participantList(iAllocate)
            End If

            tempJudgeList.Add(Judge & Hs & EmailDomain)

            For Each particpantType As String In participantTypes.Keys
                Dim maxUser As Integer = participantTypes(particpantType)

                For j As Integer = 1 To maxUser
                    tempParticipantList.Add(particpantType & Hs & "_" & j & EmailDomain)
                Next
            Next

            If judgeList.ContainsKey(iAllocate) Then
                judgeList(iAllocate) = tempJudgeList
            Else
                judgeList.Add(iAllocate, tempJudgeList)
            End If

            If participantList.ContainsKey(iAllocate) Then
                participantList(iAllocate) = tempParticipantList
            Else
                participantList.Add(iAllocate, tempParticipantList)
            End If

            If iAllocate = iMachines Then iAllocate = 0

        Next

        Dim sPath As String = TBpath.Text & "\"
        File.WriteAllLines(sPath & "BP01_3.csv", hList.ToArray)

        For i As Integer = 1 To iMachines
            File.WriteAllLines(sPath & "BP06_JudgeAttendHearing_" & i & ".csv", judgeList(i).ToArray)
            File.WriteAllLines(sPath & "BP07_ParticipantAttendHearing_" & i & ".csv", participantList(i).ToArray)
        Next

        GenerateJmx(sPath, hList.Count)
        Bgo.Visible = True
    End Sub

    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load


        Dim config As AppConfig = New AppConfig()
        TenantId = config.GetProperty("TenantId")
        ClientId = config.GetProperty("ClientId")
        ClientSecret = config.GetProperty("ClientSecret")
        ApiUrl = config.GetProperty("ApiUrl")
        TBdate.Text = Date.Now.ToString("yyyy-MM-dd")
        TBtime.Text = Date.Now.AddHours(-1).AddMinutes(15).ToString("HH:mm")

        cmbEnv.SelectedIndex = 0

        participants.Add("Individual")
        participants.Add("Representative")
        participants.Add("Observer")
        participants.Add("PanelMember")


        participantCaseHearingRole.Add("Individual_1", CreateCaseHearingRole("Applicant", "Applicant LIP", String.Empty, String.Empty))
        participantCaseHearingRole.Add("Individual_2", CreateCaseHearingRole("Respondent", "Respondent LIP", String.Empty, String.Empty))
        participantCaseHearingRole.Add("Representative_1", CreateCaseHearingRole("Applicant", "Representative", "Solicitor AA - BB (${pHearingId})", "Individual ${pRepresentativeId_1}"))
        participantCaseHearingRole.Add("Representative_2", CreateCaseHearingRole("Respondent", "Representative", "Solicitor BB - AA (${pHearingId})", "Individual ${pRepresentativeId_2}"))
        participantCaseHearingRole.Add("Observer", CreateCaseHearingRole("Observer", "Observer", String.Empty, String.Empty))
        participantCaseHearingRole.Add("PanelMember", CreateCaseHearingRole("Panel Member", "Panel Member", String.Empty, String.Empty))

        UpdateParticipants()

    End Sub

    Private Function CreateCaseHearingRole(caseRole As String, hearingRole As String, reference As String, representee As String)
        Dim caseHearingRole As CaseHearingRole = New CaseHearingRole With {
            .CaseRole = caseRole,
            .HearingRole = hearingRole,
            .Reference = reference,
            .Representee = representee
        }

        Return caseHearingRole
    End Function

    Private Sub GenerateJmx(filePath As String, noOfHearings As Integer)

        Dim fs As New FileStream(CreateHearingJmxFile, FileMode.Open, FileAccess.ReadWrite)
        Dim xmlDoc As New XmlDocument
        Dim xmlNode As XmlNodeList
        xmlDoc.Load(fs)
        xmlNode = xmlDoc.GetElementsByTagName("HTTPSamplerProxy")

        Dim httpSampleProxy = GetElementByAttributeValue(xmlNode, "testname", "Create Hearing")
        Dim collectionProp = GetElementByAttributeValue(httpSampleProxy, "name", "HTTPsampler.Arguments").Item(0).ChildNodes.Item(0).ChildNodes
        Dim jsonObjArg = GetElementByAttributeValue(collectionProp, "name", "Argument.value")

        Dim createHearing As CreateHearing = JsonConvert.DeserializeObject(Of CreateHearing)(jsonObjArg.Item(0).InnerText)

        For Each participant In participants
            For i = 1 To participantTypes(UserPrefix & participant)
                createHearing.participants.Add(CreateParticipant(participant, i))
            Next
        Next

        jsonObjArg.Item(0).InnerText = JsonConvert.SerializeObject(createHearing)

        xmlNode = xmlDoc.GetElementsByTagName("ThreadGroup")
        Dim threadGroup = GetElementByAttributeValue(xmlNode, "testname", "Thread Group")
        Dim numOfThreads = GetElementByAttributeValue(threadGroup, "name", "ThreadGroup.num_threads")
        numOfThreads.Item(0).InnerText = noOfHearings
        Dim rampTime = GetElementByAttributeValue(threadGroup, "name", "ThreadGroup.ramp_time")
        rampTime.Item(0).InnerText = noOfHearings * 2

        Dim apiUrlEnv As String = ApiUrl.Replace("dev", cmbEnv.Text).ToLower
        xmlNode = xmlDoc.GetElementsByTagName("Arguments")
        Dim arguments = GetElementByAttributeValue(xmlNode, "testname", "User Defined Variables").Item(0).ChildNodes
        Dim hResource = GetElementByAttributeValue(arguments, "name", "hResource")
        Dim hResourceValue = GetElementByAttributeValue(hResource, "name", "Argument.value")
        hResourceValue.Item(0).InnerText = apiUrlEnv

        Dim uAppAPI = GetElementByAttributeValue(arguments, "name", "uAppAPI")
        Dim uAppAPIValue = GetElementByAttributeValue(uAppAPI, "name", "Argument.value")
        uAppAPIValue.Item(0).InnerText = apiUrlEnv

        Dim uTennantId = GetElementByAttributeValue(arguments, "name", "uTennantId")
        Dim uTennantIdValue = GetElementByAttributeValue(uTennantId, "name", "Argument.value")
        uTennantIdValue.Item(0).InnerText = TenantId

        Dim hClientId = GetElementByAttributeValue(arguments, "name", "hClientId")
        Dim hClientIdValue = GetElementByAttributeValue(hClientId, "name", "Argument.value")
        hClientIdValue.Item(0).InnerText = ClientId

        Dim hClientSecret = GetElementByAttributeValue(arguments, "name", "hClientSecret")
        Dim hClientSecretValue = GetElementByAttributeValue(hClientSecret, "name", "Argument.value")
        hClientSecretValue.Item(0).InnerText = ClientSecret

        xmlNode = xmlDoc.GetElementsByTagName("CSVDataSet")
        Dim csvDataset = GetElementByAttributeValue(xmlNode, "testname", "3 party Data Set Config")
        Dim fileName = GetElementByAttributeValue(csvDataset, "name", "filename")
        fileName.Item(0).InnerText = filePath & "BP01_3.csv"

        Dim sb = New StringBuilder()
        For i As Integer = 1 To iMaxNoOfUsers
            sb.Append(",pRepresentativeId_" & i)
        Next

        Dim variableNames = GetElementByAttributeValue(csvDataset, "name", "variableNames")
        variableNames.Item(0).InnerText = "pHearingId,pHearingDate,pHearingTime,pHearingDuration,pJudgeId," & sb.ToString()

        xmlDoc.Save(filePath & CreateHearingJmxFile)
        fs.Close()
    End Sub

    Private Function CreateParticipant(hearingRole As String, userId As String)
        Dim lastName As String = hearingRole & "${pHearingId}${pRepresentativeId_" & userId & "}"
        Dim displayName As String = UserPrefix & lastName
        Dim email As String = displayName & EmailDomain
        Dim roleKey As String = hearingRole

        If hearingRole = "Individual" Or hearingRole = "Representative" Then

            If userId Mod 2 > 0 Then
                roleKey &= "_1"
            Else
                roleKey &= "_2"
            End If

        End If

        Dim caseHearingRole As CaseHearingRole = participantCaseHearingRole(roleKey)

        Dim particpant As Participant = New Participant With {
            .title = "Mr",
            .first_name = UserPrefix,
            .middle_names = String.Empty,
            .telephone_number = "01234 567890",
            .last_name = lastName,
            .contact_email = email,
            .username = email,
            .display_name = displayName,
            .case_role_name = caseHearingRole.CaseRole,
            .hearing_role_name = caseHearingRole.HearingRole,
            .reference = caseHearingRole.Reference,
            .representee = caseHearingRole.Representee,
            .organisation_name = "PerfOrg",
            .house_number = "123",
            .street = "London Road",
            .postcode = "SW1 1WS",
            .city = "London",
            .county = "London"
        }

        Return particpant
    End Function


    Private Function GetElementByAttributeValue(xmlNode As XmlNodeList, name As String, value As String) As XmlNodeList
        For i = 0 To xmlNode.Count - 1
            If xmlNode(i).Attributes.GetNamedItem(name).Value = value Then
                Return xmlNode(i).ChildNodes
            End If
        Next
        Return Nothing
    End Function

    Private Sub UpdateParticipants()
        iMaxNoOfUsers = 1

        For Each participant In participants
            participant = UserPrefix & participant
            Dim txtField As TextBox = CType(TPdataBuilder.Controls(participant), TextBox)
            Dim noOfUser As Integer = CType(txtField.Text, Integer)

            If noOfUser <> participantTypes(participant) Then
                participantTypes(participant) = noOfUser
            End If

            If iMaxNoOfUsers < participantTypes(participant) Then
                iMaxNoOfUsers = participantTypes(participant)
            End If
        Next

    End Sub

    Private Sub Bcalculate_Click(sender As Object, e As EventArgs) Handles Bcalculate.Click

        Dim iUserStart As Integer = 0
        Dim iHearings As Integer = 0
        Dim sIssue As String = "Not found: " & TBpath.Text
        If Directory.Exists(TBpath.Text) = False Then
            MsgBox(sIssue, MsgBoxStyle.OkOnly, "Directory")
            Exit Sub
        End If

        sIssue = "Issues: Machines Users Hearings"
        Try
            iMachines = CInt(TBmachines.Text)
            sIssue = sIssue.Replace(" Machines", "")
            iUserStart = CInt(TBuser.Text)
            sIssue = sIssue.Replace(" Users", "")
            iHearings = CInt(tbHearings.Text)
            sIssue = sIssue.Replace(" Hearings", "")
        Catch ex As Exception
            MsgBox(sIssue, MsgBoxStyle.OkOnly, "Schedule")
            Exit Sub
        End Try

        If iMachines < 1 Then
            MsgBox("Machines < 1", MsgBoxStyle.OkOnly, "Schedule")
            Exit Sub
        End If

        If iHearings < 1 Then
            MsgBox("Hearings < 1", MsgBoxStyle.OkOnly, "Schedule")
            Exit Sub
        End If

        Dim dSchedule As Decimal = iHearings / iMachines
        Dim iSchedule As Integer = Math.Round(dSchedule)
        'If (Integer.TryParse(dSchedule.ToString(), iSchedule)) = False Then
        '    MsgBox("Illegal: Hearings/ Machines ratio", MsgBoxStyle.OkOnly, "Schedule")
        '    Exit Sub
        'End If

        UpdateParticipants()

        iFirst = iUserStart
        iLast = iFirst + iHearings - 1

        Dim totalNoOfUsers As Integer = 1

        For Each noOfUser As Integer In participantTypes.Values
            totalNoOfUsers += noOfUser
        Next

        Dim sMessage As String = "Each Judge file will manage " & iSchedule.ToString & " judges/Hearings"
        sMessage += Environment.NewLine & "Each Participant file will manage " & (iSchedule * (totalNoOfUsers - 1)).ToString & " participants"
        sMessage += Environment.NewLine & "Total of " & (iHearings * totalNoOfUsers).ToString & " User load"
        sMessage += Environment.NewLine & "Over " & iMachines.ToString & " load generators"
        sMessage += Environment.NewLine & "Users " & iFirst.ToString & " to " & iLast.ToString

        If MsgBox(sMessage, MsgBoxStyle.OkCancel, "Schedule") = MsgBoxResult.Ok Then Bgo.Enabled = True

    End Sub

    Private Sub TBdate_TextChanged(sender As Object, e As EventArgs)
        Bgo.Enabled = False

    End Sub

    Private Sub TBtime_TextChanged(sender As Object, e As EventArgs)
        Bgo.Enabled = False
    End Sub

    Private Sub TBuser_TextChanged(sender As Object, e As EventArgs)
        Bgo.Enabled = False
    End Sub

    Private Sub tbHearings_TextChanged(sender As Object, e As EventArgs)
        Bgo.Enabled = False
    End Sub

    Private Sub TBmachines_TextChanged(sender As Object, e As EventArgs)
        Bgo.Enabled = False
    End Sub

    Private Sub Banalyse_Click(sender As Object, e As EventArgs) Handles Banalyse.Click

        If Directory.Exists(TBresultFolder.Text) = False Then Exit Sub

        passed.Clear()
        failed.Clear()

        Dim di As New IO.DirectoryInfo(TBresultFolder.Text)
        Dim diar1 As IO.FileInfo() = di.GetFiles("*.csv")
        Dim dra As IO.FileInfo

        Dim resultFiles As New List(Of String)
        Dim dataFiles As New List(Of String)
        For Each dra In diar1
            If dra.Name.ToUpper.Contains("RUN") Then
                resultFiles.Add(dra.FullName)
            Else
                dataFiles.Add(dra.FullName)
            End If
        Next

        For Each rF As String In resultFiles
            Dim fName As String = Split(Split(rF, "BP").Last, "_R").First
            For Each dF As String In dataFiles
                If dF.Contains(fName) Then
                    Dim rLines As List(Of String) = File.ReadAllLines(rF).ToList
                    Dim dLines As List(Of String) = File.ReadAllLines(dF).ToList
                    Dim lastThread As String = ""
                    For i As Integer = 1 To dLines.Count
                        Dim usr As String = dLines(i - 1)
                        Dim userJourney As New List(Of String)
                        Dim failedUser As Boolean = False
                        Dim p As String = "Passed"
                        For Each rl As String In rLines
                            If rl = "" Then Continue For
                            Dim r As List(Of String) = Split(rl, ",").ToList
                            If rl.Contains("Thread Group 1-" & i.ToString & ",") Then
                                If IsNumeric(r(0)) = False Then
                                    Dim failedTest As String = Replace(userJourney.Last, "Passed", "Failed")
                                    userJourney.RemoveAt(userJourney.Count - 1)
                                    userJourney.Add(failedTest)
                                    failedUser = True
                                    p = "Failed"
                                    Continue For
                                End If
                                Dim epoch As String = UnixToDateTime(r(0)).ToString("yyyy-MM-dd HH:mm:ss")
                                If rl.Contains("ScriptException") Then
                                    failedUser = True
                                    p = "Failed"
                                End If
                                lastThread = r(2)
                                userJourney.Add(epoch & "," & lastThread & "," & p)
                            End If
                        Next
                        lastThread = ""
                        If failedUser = True Then
                            failed.Add(usr, userJourney)
                        Else
                            passed.Add(usr, userJourney)
                        End If
                        failedUser = False
                        userJourney = New List(Of String)
                    Next
                    Exit For
                End If
            Next
        Next


        'TBFails.AppendText(r(0) & " - " & Ltest.Text & " @ " & r(1) & Environment.NewLine)
        loading = True
        CBpassed.Items.Clear()
        CBfailed.Items.Clear()
        For Each k As String In passed.Keys
            CBpassed.Items.Add(k)
        Next
        TBFails.Clear()
        For Each k As String In failed.Keys
            CBfailed.Items.Add(k)
            Dim fList As List(Of String) = failed(k)
            Dim lastPass As String = ""
            For Each f As String In fList
                Dim r As List(Of String) = Split(f, ",").ToList
                If r(2) = "Failed" Then
                    Dim failText As String = lastPass & " - " & Split(r(0), " ").Last
                    If lastPass = "" Then failText = "@ " & r(0)
                    failText += " " & k & " - " & r(1)
                    TBFails.AppendText(failText & Environment.NewLine)
                End If
                lastPass = r(0)
            Next

        Next
        loading = False
    End Sub
    Public Function UnixToDateTime(strUnixTime As String) As DateTime

        Dim nTimestamp As Double = strUnixTime.Substring(0, 10)
        Dim nDateTime As DateTime = New DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Local)
        nDateTime = nDateTime.AddSeconds(nTimestamp)

        Return nDateTime

    End Function

    Private Sub CBpassed_SelectedIndexChanged(sender As Object, e As EventArgs) Handles CBpassed.SelectedIndexChanged
        If loading = True Then Exit Sub
        If CBpassed.Text = "" Then Exit Sub
        Dim steps As List(Of String) = passed(CBpassed.Text)
        LoadGrid(steps)
        Ltest.Text = CBpassed.Text
        Lwhat.Text = "----"
        Lwhen.Text = "----"
    End Sub

    Private Sub CBfailed_SelectedIndexChanged(sender As Object, e As EventArgs) Handles CBfailed.SelectedIndexChanged
        If loading = True Then Exit Sub
        If CBfailed.Text = "" Then Exit Sub
        Dim steps As List(Of String) = failed(CBfailed.Text)
        Ltest.Text = CBfailed.Text
        Lwhen.Text = "----"
        LoadGrid(steps)
    End Sub

    Private Sub LoadGrid(steps As List(Of String))
        DGVresults.Rows.Clear()
        For Each s As String In steps
            Dim r As List(Of String) = Split(s, ",").ToList
            DGVresults.Rows.Add(r(0), r(1), r(2))
            If r(2) = "Failed" AndAlso Lwhen.Text = "----" Then
                Lwhat.Text = "Failed " & r(1)
                Lwhen.Text = "@ " & r(0)
            End If
        Next
        Application.DoEvents()
    End Sub

    Private Sub Bbrowse_Click(sender As Object, e As EventArgs) Handles Bbrowse.Click
        FolderBrowserDialog1.SelectedPath = "C:\TP Projects\MoJ\jMeter Deployed"
        If FolderBrowserDialog1.ShowDialog() = DialogResult.OK Then
            If FolderBrowserDialog1.SelectedPath <> "" Then TBpath.Text = FolderBrowserDialog1.SelectedPath
        End If

    End Sub

    Private Sub Bbrowseresults_Click(sender As Object, e As EventArgs) Handles Bbrowseresults.Click
        FolderBrowserDialog1.SelectedPath = "C:\TP Projects\MoJ\jMeter Deployed"
        If FolderBrowserDialog1.ShowDialog() = DialogResult.OK Then
            If FolderBrowserDialog1.SelectedPath <> "" Then TBresultFolder.Text = FolderBrowserDialog1.SelectedPath
        End If
    End Sub

    Private Sub Label10_Click(sender As Object, e As EventArgs) Handles Label10.Click

    End Sub

    Private Sub TextBox1_TextChanged(sender As Object, e As EventArgs) Handles TPIndividual.TextChanged

    End Sub

    Private Sub TPdataBuilder_Click(sender As Object, e As EventArgs) Handles TPdataBuilder.Click

    End Sub
End Class
