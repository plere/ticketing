# create table if not exists place
# (
#     id                bigint auto_increment comment '아이디'
#         primary key,
#     address           varchar(255) null comment '주소',
#     created_at        datetime(6)  null comment '생성일',
#     name              varchar(255) null comment '장소명',
#     total_seats_count int          not null comment '총 좌석 수'
# );
#
# create table if not exists place_seat
# (
#     id         bigint auto_increment comment '아이디'
#         primary key,
#     created_at datetime(6)  null comment '생성일',
#     floor      int          null comment '층 정보',
#     row_count  int          null comment '한행에 속한 좌석 수',
#     row_name   varchar(255) null comment '행 이름',
#     row_order  int          null comment '행 순서',
#     place_id   bigint       null comment '장소 정보',
#     foreign key (place_id) references place (id)
# );
#
# create table if not exists concert
# (
#     id                   bigint auto_increment comment '아이디'
#         primary key,
#     created_at           datetime(6)                     null comment '생성일',
#     detail_info          varchar(255)                    null comment '상세정보',
#     name                 varchar(255)                    null comment '콘서트명',
#     open_time            datetime(6)                     null comment '유저 화면에 노출되는 시점',
#     place_id             bigint                          null comment '장소 pk id',
#     running_time         int                             not null comment '러닝타임',
#     state                enum ('CLOSE', 'OPEN', 'READY') null comment '상태',
#     ticketing_start_time datetime(6)                     null comment '티케팅 시작일',
#     updated_at           datetime(6)                     null comment '수정일'
# );
#
# create table if not exists concert_round
# (
#     id              bigint auto_increment comment '아이디'
#         primary key,
#     created_at      datetime(6) null comment '생성일',
#     sequence_number int         null comment '회차 순서',
#     start_date_time datetime(6) null comment '공연일',
#     concert_id      bigint      null comment '콘서트 정보',
#     foreign key (concert_id) references concert (id)
# );
#
# create table if not exists concert_seat_grade
# (
#     id         bigint auto_increment comment '아이디'
#         primary key,
#     created_at datetime(6)  null comment '생성일',
#     name       varchar(255) null comment '등급명',
#     price      int          null comment '가격',
#     concert_id bigint       null comment '콘서트정보',
#     foreign key (concert_id) references concert (id)
# );
#
# create table if not exists concert_seat
# (
#     id         bigint auto_increment comment '아이디'
#         primary key,
#     column_num int                                         not null comment '열번호',
#     concert_id bigint                                      not null,
#     created_at datetime(6)                                 null comment '생성일',
#     floor      int                                         null comment '층 정보',
#     row_count  int                                         null comment '한행에 속한 좌석 수',
#     row_name   varchar(255)                                null comment '행 이름',
#     row_order  int                                         null comment '행 순서',
#     state      enum ('EMPTY', 'RESERVE', 'SELECT', 'SOLD') not null comment '좌석 상태',
#     grade_id   bigint                                      null comment '좌석 등급',
#     foreign key (grade_id) references concert_seat_grade (id)
# );
#
#
#
#
