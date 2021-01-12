
Declare @scheduledDateTime  as DateTime, @StartTime as DateTime, @EndTime as DateTime


SET @scheduledDateTime = '2021-01-07 11:55:00' SET  @StartTime = '2021-01-07 11:55:00'  SET  @EndTime = '2021-01-07 16:55:00'
-- SET  @scheduledDateTime = '2021-01-06 15:35:00'  SET  @StartTime =  '2021-01-06 15:35:00'   SET  @EndTime =  '2021-01-06 21:35:00'
--	SET  @scheduledDateTime = '2021-01-06 10:46:00'  SET  @StartTime = '2021-01-06 10:46:00'  SET  @EndTime = '2021-01-06 14:46:00'
 -- SET  @scheduledDateTime = '2021-01-05 14:21:00'  SET  @StartTime = '2021-01-05 14:21:00' SET  @EndTime =  '2021-01-05 20:21:00'
 --SET  @scheduledDateTime = '2021-01-05 11:54:00' SET  @StartTime = '2021-01-05 11:54:00' SET  @EndTime =  '2021-01-05 14:54:00'
--  SET  @scheduledDateTime = '2021-01-04 13:58:00' SET  @StartTime = '2021-01-04 13:58:00' SET  @EndTime = '2021-01-04 17:40:00'
-- SET  @scheduledDateTime = '2021-01-04 11:01:00' SET  @StartTime = '2021-01-04 11:01:00' SET  @EndTime = '2021-01-04 13:40:00'
 --SET  @scheduledDateTime = '2020-12-23 15:29:00' --SET  @StartTime = '2020-12-23 16:00:00' SET  @EndTime = '2020-12-23 19:00:00' 
--SET  @scheduledDateTime = '2020-12-22 09:46:00' SET  @StartTime = '2020-12-22 14:25:00' SET  @EndTime = '2020-12-22 16:10:00' 
--SET  @scheduledDateTime = '2020-12-22 09:46:00' SET  @StartTime = '2020-12-22 17:15:00' SET  @EndTime = '2020-12-22 19:00:00' 

 
Drop table #ParticipantsFailedTransfer
Drop table #Hearings

select  c.id as ConferenceId,  c.casenumber, p.id as ParticipantId, p.displayname, 
'0000-00-00 00:00:00.0000000' as Joined, '0000-00-00 00:00:00.0000000' as Disconnected, 
'0000-00-00 00:00:00.0000000' as HearingStarted, 0 as ParticipantTransfered
into #Hearings
from conference c  
 join  Participant p
 on p.ConferenceId = c.id 
where scheduleddatetime = @scheduledDateTime
order by c.casenumber 


Update h
Set h.ParticipantTransfered = 1
from #Hearings h
join Event e 
on e.conferenceid = h.conferenceid
and e.participantid = h.participantid
and 	e.EventType = 3

Update h
Set h.HearingStarted = convert(varchar, e.ExternalTimeStamp , 20) 
from #Hearings h
join Event e 
on e.conferenceid = h.conferenceid
and e.participantid = h.participantid
and 	e.EventType = 17
where e.Timestamp >= @StartTime and  Timestamp < @EndTime

Update h
Set h.Joined = convert(varchar, e.ExternalTimeStamp , 20) 
from #Hearings h
join Event e 
on e.conferenceid = h.conferenceid
and e.participantid = h.participantid
and 	e.reason = 'Judge joined'
where e.Timestamp >= @StartTime and  Timestamp < @EndTime

Update h
Set h.Disconnected = convert(varchar, e.ExternalTimeStamp , 20) 
from #Hearings h
join Event e 
on e.conferenceid = h.conferenceid
and e.participantid = h.participantid
and e.reason = 'Judge has left the hearing'
and e.id = (Select max(id) From event where  conferenceid = h.conferenceid and participantid = h.participantid and reason = 'Judge has left the hearing' and Timestamp >= @StartTime and  Timestamp < @EndTime)
where e.Timestamp >= @StartTime and  e.Timestamp < @EndTime

Update h
Set h.Joined = convert(varchar, e.ExternalTimeStamp , 20) 
from #Hearings h
join Event e 
on e.conferenceid = h.conferenceid
and e.participantid = h.participantid
and 	e.reason = 'Civilian joined'
where e.Timestamp >= @StartTime and  Timestamp < @EndTime


Update h
Set h.Disconnected = convert(varchar, e.ExternalTimeStamp , 20) 
from #Hearings h
join Event e 
on e.conferenceid = h.conferenceid
and e.participantid = h.participantid
and 	e.reason = 'Civilian has disconnected'
where e.Timestamp >= @StartTime and  Timestamp < @EndTime



select   e.*  into #ParticipantsDisconnected from #hearings h
join Event e
on e.ConferenceId = h.ConferenceId
and e.ParticipantId = h.ParticipantId
where  E.Reason = 'Civilian has disconnected'

select * from #hearings 
where joined = '0000-00-00 00:00:00.0000000'
and disconnected = '0000-00-00 00:00:00.0000000' 
and DisplayName   like '%clerk%'
order by DisplayName


select * from #hearings 
where --joined = '0000-00-00 00:00:00.0000000' and
disconnected = '0000-00-00 00:00:00.0000000' 
and DisplayName not  like '%clerk%'
order by CaseNumber

select *, DateDiff(mi,convert(datetime, Joined),convert(datetime, Disconnected))  as TimeTaken from #hearings  
where DisplayName   like '%clerk%'
and  joined != '0000-00-00 00:00:00.0000000' and disconnected != '0000-00-00 00:00:00.0000000'
--and DateDiff(mi,convert(datetime, Joined),convert(datetime, Disconnected)) >= 3
--order by casenumber
order by DateDiff(mi,convert(datetime, Joined),convert(datetime, Disconnected))


select * from #hearings  where DisplayName   like '%clerk%' and disconnected = '0000-00-00 00:00:00.0000000'

select * , DateDiff(mi,convert(datetime, Joined),convert(datetime, Disconnected))  as TimeTaken
from #hearings 
where DisplayName  not like '%clerk%'
and joined != '0000-00-00 00:00:00.0000000' and disconnected != '0000-00-00 00:00:00.0000000' 
--and ParticipantId  not in ( select  ParticipantId from #ParticipantsFailedTransfer)
--and DateDiff(mi,convert(datetime, Joined),convert(datetime, Disconnected)) >= 0
order by TimeTaken

select * from #hearings 
where --DisplayName  not like '%clerk%' and
 disconnected = '0000-00-00 00:00:00.0000000' 
and ParticipantId  not in ( select  ParticipantId from #ParticipantsFailedTransfer)
order by casenumber
 

select  * from event e 
where ParticipantId in ( select  ParticipantId from #ParticipantsFailedTransfer)
and  E.Reason = 'Civilian has failed to transfer'


/*  

SELECT * FROM #HEARINGS WHERE DisplayName not  like '%clerk%' 

select top 200 * from Conference order by scheduleddatetime  desc

SELECT max(disconnected) FROM #HEARINGS WHERE PARTICIPANTTRANSFERED = 0


select * from event where conferenceid = '75606C00-541A-4038-B0D1-3640AA7577F2'  

C9D024C3-0B24-4670-83BA-09B6E9736636
B5AC273C-FFCE-466E-9474-05F9AC14236B
3D153EB7-C37A-46C8-BB14-7F0A948DA739
A91064D4-E6CB-4970-9360-750EFADA7229
E6671793-D1AC-4CDC-A7F0-14BCDD08205D
75606C00-541A-4038-B0D1-3640AA7577F2
16DB7BA3-8538-4FDE-A526-8B6462011CEF
75606C00-541A-4038-B0D1-3640AA7577F2

select * from participant where id = 'B7F08ECD-FD7A-4B37-A5E9-F9E7BA054DBA'
 
 
select   e.*    from #hearings h
join Event e
on e.ConferenceId = h.ConferenceId
--and e.ParticipantId = h.ParticipantId
where  E.Reason = 'Hearing has been manually ended by a judge or a vho'


 select  * from event where Timestamp >= '2020-12-22 10:00:00' order by timestamp asc
 */