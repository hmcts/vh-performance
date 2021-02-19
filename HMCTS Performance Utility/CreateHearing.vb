Public Class CreateHearing
    Public participants As List(Of Participant)
    Public cases As List(Of CaseDetail)
    Public scheduled_date_time As String
    Public scheduled_duration As String
    Public hearing_venue_name As String
    Public case_type_name As String
    Public hearing_type_name As String
    Public hearing_room_name As String
    Public other_information As String
    Public created_by As String
    Public questionnaire_not_required As Boolean
    Public audio_recording_required As Boolean
End Class