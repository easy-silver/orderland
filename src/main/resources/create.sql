-- 회원(member) 테이블 생성
create table member (
    member_no bigint not null auto_increment,
    username varchar(20) not null,
    nickname varchar(30) not null,
    password varchar(100) not null,
    tel_no varchar(20) not null,
    email varchar(100) not null,
    gender varchar(255),
    enabled bit,
    primary key (member_no)
) engine=InnoDB

-- 주문(orders) 테이블 생성
create table orders (
    order_no varchar(12) not null,
    product_name varchar(100) not null,
    payment_date datetime not null,
    member_no bigint not null,
    primary key (order_no)
) engine=InnoDB

alter table orders 
add constraint fk_orders_member 
foreign key (member_no) 
references member (member_no)
