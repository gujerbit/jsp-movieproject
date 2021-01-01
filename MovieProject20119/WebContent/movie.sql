drop table movie;
drop table schedule;
drop table Room;
drop table ticket;
drop table member;

-- 01 : 액션 / 02 : 로맨스 / 03 : 코미디 / 04 : 스릴러 / 05 : 애니메이션
create table movie(
	movieNo		number primary key, --영화 번호
	movieName	varchar2(20), --영화 제목
	category	number, --장르
	runtime		number, --120분
	img			varchar2(50), --이미지 파일 불러오기위한 제목
	info		varchar2(200) --영화 정보
);

insert into movie values(10000,'어벤저스',01,120,'1.jpg','재밌다 ');
insert into movie values(10001,'노팅힐',02, 120 , '2.jpg','감동적이다 ');
insert into movie values(10002,'아이언맨',01, 120 , '3.jpg','멋있따');
insert into movie values(10003,'겨울왕국2',05, 130 , '4.jpg','재밌다 ');
insert into movie values(10004,'엑시트',03, 140 , '5.jpg','킬링타임 ');
insert into movie values(10005,'반도',04, 155 , '6.jpg','잘생겼다 ');
insert into movie values(10006,'23아이덴티디',04, 150 , '7.jpg','꿀잼');

SELECT COUNT(movieName) FROM movie;

select schNo, movieName,
decode(category, 01, '액션', 02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션'), m.runtime, img, info, to_char(runday, 'mm/dd') as 날짜,
to_char(runday, 'HH:24:MI') as 상영시간, roomNo
from movie m, schedule s where m.movieNo = s.movieNo and m.movieNo = 10000;

select decode(category, 01, '액션', 02, '로맨스', 03, '코미디', 04, '스릴러', 05, '애니메이션') from movie;

create table member(
	id		varchar2(20) primary key, --아이디
	pw		varchar2(20), --비밀번호
	email	varchar2(50), --이메일
	tel		varchar2(20), --전화번호
	birth	date --생년원일
);

insert into member values('admin', '5678', 'admin@admin.com', '010-5678-5678', '2003-03-17');
insert into member values('jun', '1234', 'jun@jun.com', '010-1234-5678', '2003-03-17');
insert into member values('test','1234','test@test.com','010-1234-1234','2002-05-12');

update member set pw = 'admin' where id = 'admin';

delete member where id = 'testUser';

select * from member;

create table schedule( --상영관(room) 관람시간 영화관 이랑 연결해주는 테이블
	schNo		number primary key, --스케줄 번호
	movieNo		number, --영화관 번호
	runDay		date, --상영 날짜
	runtime		number, --상영 시간 : 몇분짜리 영화?
	roomNo		number --상영관 번호
);
-- 10000
insert into schedule values(1, 10000, TO_DATE('2020/11/11 11:50', 'yyyy/mm/dd hh24:mi'), 120, 1);
insert into schedule values(2, 10000, TO_DATE('2020/11/11 1:50', 'yyyy/mm/dd hh24:mi'), 120, 1);
insert into schedule values(3, 10000, TO_DATE('2020/11/11 3:10', 'yyyy/mm/dd hh24:mi'), 120, 1);
insert into schedule values(4, 10000, TO_DATE('2020/11/11 8:50', 'yyyy/mm/dd hh24:mi'), 120, 1);
-- 10001
insert into schedule values(5, 10001, TO_DATE('2020/11/11 11:50', 'yyyy/mm/dd hh24:mi'), 120, 2);
insert into schedule values(6, 10001, TO_DATE('2020/11/11 1:50', 'yyyy/mm/dd hh24:mi'), 120, 2);
insert into schedule values(7, 10001, TO_DATE('2020/11/11 3:10', 'yyyy/mm/dd hh24:mi'), 120, 2);
insert into schedule values(8, 10001, TO_DATE('2020/11/11 8:50', 'yyyy/mm/dd hh24:mi'), 120, 2);

create table Room( --상영관
	roomNo		number, --상영관 번호 
	schNo		number, --스케줄 번호
	seatCnt		number --그 상영관에 얼마나 좌석이 예매가 되어있는지 카운트
);

-- 10000
insert into Room values(1, 1, 0); --상영관 스케줄 번호 예매좌석 카운트
insert into Room values(1, 2, 0);
insert into Room values(1, 3, 0);
insert into Room values(1, 4, 0);

-- 10001
insert into Room values(2, 5, 0);
insert into Room values(2, 6, 0);
insert into Room values(2, 7, 0);
insert into Room values(2, 8, 0); 

update Room set seatCnt = 2;

create table ticket( --영화 구매했을때 저장되는 테이블
	ticketNo		number primary key, --티켓 번호
	ticketDate		date, --결제한 날짜
	schNo			number, --스케줄 번호
	seatNo			number, --내가 선택한 좌석 번호
	id				varchar2(20) --회원 아이디
);

insert into ticket values(1, sysdate, 1, 15, 'test');
insert into ticket values(2, sysdate, 1, 14, 'test');
--티켓이 insert 될 때마다 seatCnt(예매한 좌석수 ) 갯수도 증가해야한다 
update Room r set seatCnt = seatCnt+1 where roomNo = 1 and r.schNo in(select schNo from ticket where schNo = 1);

SELECT t.seatNo FROM schedule s, ticket t WHERE s.schNo = 2 AND s.schNo = t.schNo;

select * from Room;
select * from ticket;

delete ticket where schNo != 1;

SELECT s.schNo, s.runday, s.roomNo, s.runtime, r.seatCnt FROM movie m, schedule s, Room r WHERE m.movieNo = s.movieNo AND s.movieNo = 10000;