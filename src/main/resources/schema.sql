CREATE TABLE schedule(
	id bigint not null,
	name varchar(255) not null,
	start_date datetime not null,
	end_date datetime not null,
	user_id bigint not null,
	completed varchar(12) not null,
	primary key (id)
);
