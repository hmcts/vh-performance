<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class HMCTSPerformanceUtility
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.TabControl1 = New System.Windows.Forms.TabControl()
        Me.TPdataBuilder = New System.Windows.Forms.TabPage()
        Me.TPPanelMember = New System.Windows.Forms.TextBox()
        Me.Label13 = New System.Windows.Forms.Label()
        Me.TPObserver = New System.Windows.Forms.TextBox()
        Me.Label12 = New System.Windows.Forms.Label()
        Me.TPRepresentative = New System.Windows.Forms.TextBox()
        Me.Label11 = New System.Windows.Forms.Label()
        Me.TPIndividual = New System.Windows.Forms.TextBox()
        Me.Label10 = New System.Windows.Forms.Label()
        Me.Label6 = New System.Windows.Forms.Label()
        Me.Bbrowse = New System.Windows.Forms.Button()
        Me.Bcalculate = New System.Windows.Forms.Button()
        Me.TBdate = New System.Windows.Forms.TextBox()
        Me.TBpath = New System.Windows.Forms.TextBox()
        Me.Bgo = New System.Windows.Forms.Button()
        Me.Label5 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.TBuser = New System.Windows.Forms.TextBox()
        Me.TBtime = New System.Windows.Forms.TextBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.tbHearings = New System.Windows.Forms.TextBox()
        Me.TBmachines = New System.Windows.Forms.TextBox()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.TPresultsAnalysis = New System.Windows.Forms.TabPage()
        Me.TBFails = New System.Windows.Forms.TextBox()
        Me.Bbrowseresults = New System.Windows.Forms.Button()
        Me.Lwhat = New System.Windows.Forms.Label()
        Me.Lwhen = New System.Windows.Forms.Label()
        Me.Ltest = New System.Windows.Forms.Label()
        Me.DGVresults = New System.Windows.Forms.DataGridView()
        Me.Column1 = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Column2 = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Column3 = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.Label8 = New System.Windows.Forms.Label()
        Me.CBfailed = New System.Windows.Forms.ComboBox()
        Me.Label7 = New System.Windows.Forms.Label()
        Me.CBpassed = New System.Windows.Forms.ComboBox()
        Me.Banalyse = New System.Windows.Forms.Button()
        Me.Label9 = New System.Windows.Forms.Label()
        Me.TBresultFolder = New System.Windows.Forms.TextBox()
        Me.FolderBrowserDialog1 = New System.Windows.Forms.FolderBrowserDialog()
        Me.cmbEnv = New System.Windows.Forms.ComboBox()
        Me.Label14 = New System.Windows.Forms.Label()
        Me.TabControl1.SuspendLayout()
        Me.TPdataBuilder.SuspendLayout()
        Me.TPresultsAnalysis.SuspendLayout()
        CType(Me.DGVresults, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'TabControl1
        '
        Me.TabControl1.Controls.Add(Me.TPdataBuilder)
        Me.TabControl1.Controls.Add(Me.TPresultsAnalysis)
        Me.TabControl1.Location = New System.Drawing.Point(11, 10)
        Me.TabControl1.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TabControl1.Name = "TabControl1"
        Me.TabControl1.SelectedIndex = 0
        Me.TabControl1.Size = New System.Drawing.Size(1124, 692)
        Me.TabControl1.TabIndex = 0
        '
        'TPdataBuilder
        '
        Me.TPdataBuilder.Controls.Add(Me.Label14)
        Me.TPdataBuilder.Controls.Add(Me.cmbEnv)
        Me.TPdataBuilder.Controls.Add(Me.TPPanelMember)
        Me.TPdataBuilder.Controls.Add(Me.Label13)
        Me.TPdataBuilder.Controls.Add(Me.TPObserver)
        Me.TPdataBuilder.Controls.Add(Me.Label12)
        Me.TPdataBuilder.Controls.Add(Me.TPRepresentative)
        Me.TPdataBuilder.Controls.Add(Me.Label11)
        Me.TPdataBuilder.Controls.Add(Me.TPIndividual)
        Me.TPdataBuilder.Controls.Add(Me.Label10)
        Me.TPdataBuilder.Controls.Add(Me.Label6)
        Me.TPdataBuilder.Controls.Add(Me.Bbrowse)
        Me.TPdataBuilder.Controls.Add(Me.Bcalculate)
        Me.TPdataBuilder.Controls.Add(Me.TBdate)
        Me.TPdataBuilder.Controls.Add(Me.TBpath)
        Me.TPdataBuilder.Controls.Add(Me.Bgo)
        Me.TPdataBuilder.Controls.Add(Me.Label5)
        Me.TPdataBuilder.Controls.Add(Me.Label1)
        Me.TPdataBuilder.Controls.Add(Me.TBuser)
        Me.TPdataBuilder.Controls.Add(Me.TBtime)
        Me.TPdataBuilder.Controls.Add(Me.Label2)
        Me.TPdataBuilder.Controls.Add(Me.Label4)
        Me.TPdataBuilder.Controls.Add(Me.tbHearings)
        Me.TPdataBuilder.Controls.Add(Me.TBmachines)
        Me.TPdataBuilder.Controls.Add(Me.Label3)
        Me.TPdataBuilder.Location = New System.Drawing.Point(4, 25)
        Me.TPdataBuilder.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPdataBuilder.Name = "TPdataBuilder"
        Me.TPdataBuilder.Padding = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPdataBuilder.Size = New System.Drawing.Size(1116, 663)
        Me.TPdataBuilder.TabIndex = 0
        Me.TPdataBuilder.Text = "Data Builder"
        Me.TPdataBuilder.UseVisualStyleBackColor = True
        '
        'TPPanelMember
        '
        Me.TPPanelMember.Location = New System.Drawing.Point(467, 215)
        Me.TPPanelMember.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPPanelMember.Name = "TPPanelMember"
        Me.TPPanelMember.Size = New System.Drawing.Size(73, 22)
        Me.TPPanelMember.TabIndex = 50
        Me.TPPanelMember.Text = "1"
        Me.TPPanelMember.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'Label13
        '
        Me.Label13.AutoSize = True
        Me.Label13.Location = New System.Drawing.Point(343, 219)
        Me.Label13.Name = "Label13"
        Me.Label13.Size = New System.Drawing.Size(114, 16)
        Me.Label13.TabIndex = 51
        Me.Label13.Text = "Panel Member(s):"
        '
        'TPObserver
        '
        Me.TPObserver.Location = New System.Drawing.Point(467, 178)
        Me.TPObserver.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPObserver.Name = "TPObserver"
        Me.TPObserver.Size = New System.Drawing.Size(73, 22)
        Me.TPObserver.TabIndex = 48
        Me.TPObserver.Text = "1"
        Me.TPObserver.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'Label12
        '
        Me.Label12.AutoSize = True
        Me.Label12.Location = New System.Drawing.Point(343, 182)
        Me.Label12.Name = "Label12"
        Me.Label12.Size = New System.Drawing.Size(82, 16)
        Me.Label12.TabIndex = 49
        Me.Label12.Text = "Observer(s):"
        '
        'TPRepresentative
        '
        Me.TPRepresentative.Location = New System.Drawing.Point(467, 142)
        Me.TPRepresentative.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPRepresentative.Name = "TPRepresentative"
        Me.TPRepresentative.Size = New System.Drawing.Size(73, 22)
        Me.TPRepresentative.TabIndex = 46
        Me.TPRepresentative.Text = "1"
        Me.TPRepresentative.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'Label11
        '
        Me.Label11.AutoSize = True
        Me.Label11.Location = New System.Drawing.Point(343, 145)
        Me.Label11.Name = "Label11"
        Me.Label11.Size = New System.Drawing.Size(118, 16)
        Me.Label11.TabIndex = 47
        Me.Label11.Text = "Representative(s):"
        '
        'TPIndividual
        '
        Me.TPIndividual.Location = New System.Drawing.Point(467, 102)
        Me.TPIndividual.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPIndividual.Name = "TPIndividual"
        Me.TPIndividual.Size = New System.Drawing.Size(73, 22)
        Me.TPIndividual.TabIndex = 44
        Me.TPIndividual.Text = "1"
        Me.TPIndividual.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'Label10
        '
        Me.Label10.AutoSize = True
        Me.Label10.Location = New System.Drawing.Point(343, 106)
        Me.Label10.Name = "Label10"
        Me.Label10.Size = New System.Drawing.Size(83, 16)
        Me.Label10.TabIndex = 45
        Me.Label10.Text = "Individual(s):"
        '
        'Label6
        '
        Me.Label6.AutoSize = True
        Me.Label6.Location = New System.Drawing.Point(25, 26)
        Me.Label6.Name = "Label6"
        Me.Label6.Size = New System.Drawing.Size(38, 16)
        Me.Label6.TabIndex = 43
        Me.Label6.Text = "Path:"
        '
        'Bbrowse
        '
        Me.Bbrowse.Location = New System.Drawing.Point(544, 15)
        Me.Bbrowse.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.Bbrowse.Name = "Bbrowse"
        Me.Bbrowse.Size = New System.Drawing.Size(127, 37)
        Me.Bbrowse.TabIndex = 42
        Me.Bbrowse.Text = "Browse"
        Me.Bbrowse.UseVisualStyleBackColor = True
        '
        'Bcalculate
        '
        Me.Bcalculate.Location = New System.Drawing.Point(100, 318)
        Me.Bcalculate.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.Bcalculate.Name = "Bcalculate"
        Me.Bcalculate.Size = New System.Drawing.Size(111, 37)
        Me.Bcalculate.TabIndex = 38
        Me.Bcalculate.Text = "CALCULATE"
        Me.Bcalculate.UseVisualStyleBackColor = True
        '
        'TBdate
        '
        Me.TBdate.Location = New System.Drawing.Point(73, 68)
        Me.TBdate.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TBdate.Name = "TBdate"
        Me.TBdate.Size = New System.Drawing.Size(185, 22)
        Me.TBdate.TabIndex = 29
        Me.TBdate.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'TBpath
        '
        Me.TBpath.Location = New System.Drawing.Point(73, 23)
        Me.TBpath.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TBpath.Name = "TBpath"
        Me.TBpath.Size = New System.Drawing.Size(432, 22)
        Me.TBpath.TabIndex = 41
        Me.TBpath.Text = "C:\JMeter Scripts"
        Me.TBpath.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'Bgo
        '
        Me.Bgo.Enabled = False
        Me.Bgo.Location = New System.Drawing.Point(100, 380)
        Me.Bgo.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.Bgo.Name = "Bgo"
        Me.Bgo.Size = New System.Drawing.Size(91, 37)
        Me.Bgo.TabIndex = 30
        Me.Bgo.Text = "GO"
        Me.Bgo.UseVisualStyleBackColor = True
        '
        'Label5
        '
        Me.Label5.AutoSize = True
        Me.Label5.Location = New System.Drawing.Point(25, 142)
        Me.Label5.Name = "Label5"
        Me.Label5.Size = New System.Drawing.Size(40, 16)
        Me.Label5.TabIndex = 40
        Me.Label5.Text = "User:"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(25, 70)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(40, 16)
        Me.Label1.TabIndex = 31
        Me.Label1.Text = "Date:"
        '
        'TBuser
        '
        Me.TBuser.Location = New System.Drawing.Point(73, 142)
        Me.TBuser.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TBuser.Name = "TBuser"
        Me.TBuser.Size = New System.Drawing.Size(185, 22)
        Me.TBuser.TabIndex = 39
        Me.TBuser.Text = "301"
        Me.TBuser.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'TBtime
        '
        Me.TBtime.Location = New System.Drawing.Point(73, 102)
        Me.TBtime.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TBtime.Name = "TBtime"
        Me.TBtime.Size = New System.Drawing.Size(185, 22)
        Me.TBtime.TabIndex = 32
        Me.TBtime.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(25, 105)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(42, 16)
        Me.Label2.TabIndex = 33
        Me.Label2.Text = "Time:"
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Location = New System.Drawing.Point(25, 222)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(66, 16)
        Me.Label4.TabIndex = 37
        Me.Label4.Text = "Machines"
        '
        'tbHearings
        '
        Me.tbHearings.Location = New System.Drawing.Point(100, 180)
        Me.tbHearings.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.tbHearings.Name = "tbHearings"
        Me.tbHearings.Size = New System.Drawing.Size(160, 22)
        Me.tbHearings.TabIndex = 34
        Me.tbHearings.Text = "42"
        Me.tbHearings.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'TBmachines
        '
        Me.TBmachines.Location = New System.Drawing.Point(100, 219)
        Me.TBmachines.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TBmachines.Name = "TBmachines"
        Me.TBmachines.Size = New System.Drawing.Size(160, 22)
        Me.TBmachines.TabIndex = 36
        Me.TBmachines.Text = "3"
        Me.TBmachines.TextAlign = System.Windows.Forms.HorizontalAlignment.Center
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(25, 182)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(66, 16)
        Me.Label3.TabIndex = 35
        Me.Label3.Text = "Hearings:"
        '
        'TPresultsAnalysis
        '
        Me.TPresultsAnalysis.Controls.Add(Me.TBFails)
        Me.TPresultsAnalysis.Controls.Add(Me.Bbrowseresults)
        Me.TPresultsAnalysis.Controls.Add(Me.Lwhat)
        Me.TPresultsAnalysis.Controls.Add(Me.Lwhen)
        Me.TPresultsAnalysis.Controls.Add(Me.Ltest)
        Me.TPresultsAnalysis.Controls.Add(Me.DGVresults)
        Me.TPresultsAnalysis.Controls.Add(Me.Label8)
        Me.TPresultsAnalysis.Controls.Add(Me.CBfailed)
        Me.TPresultsAnalysis.Controls.Add(Me.Label7)
        Me.TPresultsAnalysis.Controls.Add(Me.CBpassed)
        Me.TPresultsAnalysis.Controls.Add(Me.Banalyse)
        Me.TPresultsAnalysis.Controls.Add(Me.Label9)
        Me.TPresultsAnalysis.Controls.Add(Me.TBresultFolder)
        Me.TPresultsAnalysis.Location = New System.Drawing.Point(4, 25)
        Me.TPresultsAnalysis.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPresultsAnalysis.Name = "TPresultsAnalysis"
        Me.TPresultsAnalysis.Padding = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TPresultsAnalysis.Size = New System.Drawing.Size(1116, 663)
        Me.TPresultsAnalysis.TabIndex = 1
        Me.TPresultsAnalysis.Text = "Results Analysis"
        Me.TPresultsAnalysis.UseVisualStyleBackColor = True
        '
        'TBFails
        '
        Me.TBFails.Location = New System.Drawing.Point(23, 479)
        Me.TBFails.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TBFails.Multiline = True
        Me.TBFails.Name = "TBFails"
        Me.TBFails.Size = New System.Drawing.Size(1069, 163)
        Me.TBFails.TabIndex = 25
        '
        'Bbrowseresults
        '
        Me.Bbrowseresults.Location = New System.Drawing.Point(575, 20)
        Me.Bbrowseresults.Margin = New System.Windows.Forms.Padding(4, 4, 4, 4)
        Me.Bbrowseresults.Name = "Bbrowseresults"
        Me.Bbrowseresults.Size = New System.Drawing.Size(100, 28)
        Me.Bbrowseresults.TabIndex = 24
        Me.Bbrowseresults.Text = "Browse"
        Me.Bbrowseresults.UseVisualStyleBackColor = True
        '
        'Lwhat
        '
        Me.Lwhat.AutoSize = True
        Me.Lwhat.Location = New System.Drawing.Point(436, 119)
        Me.Lwhat.Name = "Lwhat"
        Me.Lwhat.Size = New System.Drawing.Size(24, 16)
        Me.Lwhat.TabIndex = 23
        Me.Lwhat.Text = "----"
        '
        'Lwhen
        '
        Me.Lwhen.AutoSize = True
        Me.Lwhen.Location = New System.Drawing.Point(756, 119)
        Me.Lwhen.Name = "Lwhen"
        Me.Lwhen.Size = New System.Drawing.Size(24, 16)
        Me.Lwhen.TabIndex = 22
        Me.Lwhen.Text = "----"
        '
        'Ltest
        '
        Me.Ltest.AutoSize = True
        Me.Ltest.Location = New System.Drawing.Point(20, 119)
        Me.Ltest.Name = "Ltest"
        Me.Ltest.Size = New System.Drawing.Size(24, 16)
        Me.Ltest.TabIndex = 21
        Me.Ltest.Text = "----"
        '
        'DGVresults
        '
        Me.DGVresults.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.DGVresults.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.Column1, Me.Column2, Me.Column3})
        Me.DGVresults.Location = New System.Drawing.Point(23, 148)
        Me.DGVresults.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.DGVresults.Name = "DGVresults"
        Me.DGVresults.RowHeadersWidth = 62
        Me.DGVresults.RowTemplate.Height = 28
        Me.DGVresults.Size = New System.Drawing.Size(1063, 314)
        Me.DGVresults.TabIndex = 20
        '
        'Column1
        '
        Me.Column1.HeaderText = "Date Time"
        Me.Column1.MinimumWidth = 8
        Me.Column1.Name = "Column1"
        Me.Column1.Width = 150
        '
        'Column2
        '
        Me.Column2.HeaderText = "Step"
        Me.Column2.MinimumWidth = 8
        Me.Column2.Name = "Column2"
        Me.Column2.Width = 300
        '
        'Column3
        '
        Me.Column3.HeaderText = "Result"
        Me.Column3.MinimumWidth = 8
        Me.Column3.Name = "Column3"
        Me.Column3.Width = 150
        '
        'Label8
        '
        Me.Label8.AutoSize = True
        Me.Label8.Location = New System.Drawing.Point(503, 74)
        Me.Label8.Name = "Label8"
        Me.Label8.Size = New System.Drawing.Size(46, 16)
        Me.Label8.TabIndex = 19
        Me.Label8.Text = "Failed"
        '
        'CBfailed
        '
        Me.CBfailed.FormattingEnabled = True
        Me.CBfailed.Location = New System.Drawing.Point(556, 71)
        Me.CBfailed.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.CBfailed.Name = "CBfailed"
        Me.CBfailed.Size = New System.Drawing.Size(380, 24)
        Me.CBfailed.TabIndex = 18
        '
        'Label7
        '
        Me.Label7.AutoSize = True
        Me.Label7.Location = New System.Drawing.Point(20, 74)
        Me.Label7.Name = "Label7"
        Me.Label7.Size = New System.Drawing.Size(55, 16)
        Me.Label7.TabIndex = 17
        Me.Label7.Text = "Passed"
        '
        'CBpassed
        '
        Me.CBpassed.FormattingEnabled = True
        Me.CBpassed.Location = New System.Drawing.Point(83, 71)
        Me.CBpassed.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.CBpassed.Name = "CBpassed"
        Me.CBpassed.Size = New System.Drawing.Size(380, 24)
        Me.CBpassed.TabIndex = 16
        '
        'Banalyse
        '
        Me.Banalyse.Location = New System.Drawing.Point(737, 20)
        Me.Banalyse.Margin = New System.Windows.Forms.Padding(4, 4, 4, 4)
        Me.Banalyse.Name = "Banalyse"
        Me.Banalyse.Size = New System.Drawing.Size(187, 28)
        Me.Banalyse.TabIndex = 15
        Me.Banalyse.Text = "Analyse"
        Me.Banalyse.UseVisualStyleBackColor = True
        '
        'Label9
        '
        Me.Label9.AutoSize = True
        Me.Label9.Location = New System.Drawing.Point(20, 25)
        Me.Label9.Name = "Label9"
        Me.Label9.Size = New System.Drawing.Size(47, 16)
        Me.Label9.TabIndex = 14
        Me.Label9.Text = "Folder"
        '
        'TBresultFolder
        '
        Me.TBresultFolder.Location = New System.Drawing.Point(83, 21)
        Me.TBresultFolder.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.TBresultFolder.Name = "TBresultFolder"
        Me.TBresultFolder.Size = New System.Drawing.Size(487, 22)
        Me.TBresultFolder.TabIndex = 13
        Me.TBresultFolder.Text = "C:\TP Projects\MoJ\jMeter Deployed\Load Generator Results"
        '
        'cmbEnv
        '
        Me.cmbEnv.FormattingEnabled = True
        Me.cmbEnv.Items.AddRange(New Object() {"Demo", "Dev"})
        Me.cmbEnv.Location = New System.Drawing.Point(467, 70)
        Me.cmbEnv.Name = "cmbEnv"
        Me.cmbEnv.Size = New System.Drawing.Size(73, 24)
        Me.cmbEnv.TabIndex = 52
        '
        'Label14
        '
        Me.Label14.AutoSize = True
        Me.Label14.Location = New System.Drawing.Point(343, 70)
        Me.Label14.Name = "Label14"
        Me.Label14.Size = New System.Drawing.Size(85, 16)
        Me.Label14.TabIndex = 53
        Me.Label14.Text = "Environment:"
        '
        'HMCTSPerformanceUtility
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(8.0!, 16.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(1145, 706)
        Me.Controls.Add(Me.TabControl1)
        Me.Margin = New System.Windows.Forms.Padding(3, 2, 3, 2)
        Me.Name = "HMCTSPerformanceUtility"
        Me.Text = "HMCTS Performance Utility"
        Me.TabControl1.ResumeLayout(False)
        Me.TPdataBuilder.ResumeLayout(False)
        Me.TPdataBuilder.PerformLayout()
        Me.TPresultsAnalysis.ResumeLayout(False)
        Me.TPresultsAnalysis.PerformLayout()
        CType(Me.DGVresults, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)

    End Sub

    Friend WithEvents TabControl1 As TabControl
    Friend WithEvents TPdataBuilder As TabPage
    Friend WithEvents Label6 As Label
    Friend WithEvents Bbrowse As Button
    Friend WithEvents Bcalculate As Button
    Friend WithEvents TBdate As TextBox
    Friend WithEvents TBpath As TextBox
    Friend WithEvents Bgo As Button
    Friend WithEvents Label5 As Label
    Friend WithEvents Label1 As Label
    Friend WithEvents TBuser As TextBox
    Friend WithEvents TBtime As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents Label4 As Label
    Friend WithEvents tbHearings As TextBox
    Friend WithEvents TBmachines As TextBox
    Friend WithEvents Label3 As Label
    Friend WithEvents TPresultsAnalysis As TabPage
    Friend WithEvents TBFails As TextBox
    Friend WithEvents Bbrowseresults As Button
    Friend WithEvents Lwhat As Label
    Friend WithEvents Lwhen As Label
    Friend WithEvents Ltest As Label
    Friend WithEvents DGVresults As DataGridView
    Friend WithEvents Column1 As DataGridViewTextBoxColumn
    Friend WithEvents Column2 As DataGridViewTextBoxColumn
    Friend WithEvents Column3 As DataGridViewTextBoxColumn
    Friend WithEvents Label8 As Label
    Friend WithEvents CBfailed As ComboBox
    Friend WithEvents Label7 As Label
    Friend WithEvents CBpassed As ComboBox
    Friend WithEvents Banalyse As Button
    Friend WithEvents Label9 As Label
    Friend WithEvents TBresultFolder As TextBox
    Friend WithEvents FolderBrowserDialog1 As FolderBrowserDialog
    Friend WithEvents TPIndividual As TextBox
    Friend WithEvents Label10 As Label
    Friend WithEvents TPPanelMember As TextBox
    Friend WithEvents Label13 As Label
    Friend WithEvents TPObserver As TextBox
    Friend WithEvents Label12 As Label
    Friend WithEvents TPRepresentative As TextBox
    Friend WithEvents Label11 As Label
    Friend WithEvents Label14 As Label
    Friend WithEvents cmbEnv As ComboBox
End Class
