create table student.SM_ClientMaster (clientId bigint not null auto_increment, 
createdTS datetime not null, updatedTS datetime not null, clientCode varchar(255) not null,
 clientName varchar(255) not null, primary key (clientId));

create table student.SM_UserAuthorityMaster (userAuthorityId bigint not null auto_increment, 
createdTS datetime not null, 
updatedTS datetime not null, authority varchar(255) not null, 
userID bigint not null, primary key (userAuthorityId));

create table student.SM_UserMaster (userId bigint not null auto_increment, createdTS datetime not null, 
updatedTS datetime not null, accountNonExpired char(1) not null, 
accountNonLocked char(1) not null, credentialsNonExpired char(1) not null, 
email varchar(255) not null, enabled char(1) not null, 
name varchar(255) not null, password varchar(255) not null, tempPasswordIssued char(1) not null, 
username varchar(255) not null, clientID bigint not null, primary key (userId));

create index IDX_SM_UserAuthorityMaster_userid on student.SM_UserAuthorityMaster (userID);

create index IDX_SM_UserMaster_username_password on student.SM_UserMaster (username, password);

alter table student.SM_UserAuthorityMaster add constraint FK_c5d7uygl63vpmr5rkl1xpjmwh foreign key (userID) references student.SM_UserMaster (userId);

alter table student.SM_UserMaster add constraint FK_92nrtgfnxsu139fauv4b9nv87 foreign key (clientID) references student.SM_ClientMaster (clientId);