-- 모델2 게시판
select * from tab;
select * from seq;

insert into mybatisboard values(mybatisboard_seq.nextval,'홍길동',
'1234','게시판연습','내용','',mybatisboard_seq.nextval,0,0,0,sysdate);

select count(*) from model2board;

create table mybatisboard(
	board_num number,
	board_name varchar2(20),
	board_pass varchar2(15),
	board_subject varchar2(50),
	board_content varchar2(2000),
	board_file varchar2(50),
	board_re_ref number,
	board_re_lev number,
	board_re_seq number,
	board_readcount number,
	board_date timestamp,
	primary key(board_num)
);


drop sequence MYBATISBOARD_SEQ;
create sequence mybatisboard_seq
start with 1
increment by 1
nocache;

BEGIN
    FOR I IN 1..120
        LOOP
            INSERT INTO mybatisboard VALUES (mybatisboard_seq.NEXTVAL, 'jun', 'pass', '원문 ' || TO_CHAR(I), 'hi', '', mybatisboard_seq.NEXTVAL, 0, 0, 0, SYSDATE);
        END LOOP;
END;

commit;

select * from mybatisboard order by board_num;

delete from mybatisboard;