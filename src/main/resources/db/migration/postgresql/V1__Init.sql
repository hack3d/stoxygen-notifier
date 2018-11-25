create table notification (
    notifications_id  bigserial not null,
    insert_timestamp timestamp,
    insert_user varchar(255),
    modify_timestamp timestamp,
    modify_user varchar(255),
    category varchar(255) not null,
    content varchar(255) not null,
    content_type varchar(255) not null,
    description varchar(255) not null,
    sender varchar(255) not null,
    notification_severity varchar(255) not null,
    notification_status varchar(255) not null,
    primary key (notifications_id)
);

create table receiver_group (
    receiver_groups_id  bigserial not null,
    insert_timestamp timestamp,
    insert_user varchar(255),
    modify_timestamp timestamp,
    modify_user varchar(255),
    category varchar(255) not null,
    channel varchar(255) not null,
    receiver_address varchar(255) not null,
    severity varchar(255) not null,
    primary key (receiver_groups_id)
);

create table transmission (
    transmissions_id  bigserial not null,
    insert_timestamp timestamp,
    insert_user varchar(255),
    modify_timestamp timestamp,
    modify_user varchar(255),
    channel varchar(255) not null,
    receiver varchar(255) not null,
    resend_count int not null,
    transmission_status varchar(255) not null,
    sent timestamp, status varchar(255) null,
    notification_notifications_id int8,
    primary key (transmissions_id)
);

alter table transmission add constraint FK115y2tldidjkvb4pr0jtkfnsp foreign key (notification_notifications_id) references notification
