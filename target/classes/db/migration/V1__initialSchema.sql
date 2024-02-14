CREATE TABLE User(

	id varchar(255) NOT NULL,
	role varchar(20),
	username varchar(30),
	email varchar(30),
	passwd varchar(60),
	country varchar(50),
	PRIMARY KEY (id)

);


CREATE TABLE Item(

    id varchar(255) NOT NULL,
    user_id varchar(255),
    title varchar(100),
    format varchar(30),
    rpm int,
    genre varchar(30),
    year_of_release int,
    condition_of_item varchar(50),
    other_info varchar(255),
    country varchar(100),
    label varchar(50),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES User(id)

);


CREATE TABLE Orders(

    id varchar(255) NOT NULL,
    item_id varchar(255) UNIQUE,
    time_stamp varchar(100),
    user_id varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (item_id) REFERENCES Item(id)
);

CREATE TABLE Artist(

    id varchar(255) NOT NULL,
    name varchar (100),
    PRIMARY KEY(id)

);

CREATE TABLE RecordArtist(

    id varchar(255) NOT NULL,
    item_id varchar(255),
    artist_id varchar(255),
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES Item(id),
    FOREIGN KEY (artist_id) REFERENCES Artist(id)
);
