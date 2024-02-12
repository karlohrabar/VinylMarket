CREATE TABLE User(

	id varchar(255) NOT NULL,
	role varchar(20),
	username varchar(30),
	email varchar(30),
	passwd varchar(60),
	country varchar(50),
	PRIMARY KEY (id)

);

CREATE TABLE Order(

    id varchar(255) NOT NULL,
    time_stamp varchar(100),
    user_id varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);

CREATE TABLE Item(

    id varchar(255) NOT NULL,
    title varchar(100),
    format varchar(30),
    genre varchar(30),
    year_of_release int,

)