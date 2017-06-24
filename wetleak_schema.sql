DROP TABLE IF EXISTS patient;
CREATE TABLE patient (
       pname	 varchar(25),
       age	 INT,
       primary key (pname)
       );

DROP TABLE IF EXISTS sensor;
CREATE TABLE sensor (
       sensorid	    int	AUTO_INCREMENT,
       temp	    decimal(5,2),
       humidity	    decimal(5,2),
       ph	    decimal(5,2),
       movementx    decimal(5,2),
       movementy    decimal(5,2),
       movementz    decimal(5,2),
       retrieved_at TIMESTAMP	DEFAULT	CURRENT_TIMESTAMP,
       pname	    varchar(25),
       primary key(sensorid),
       foreign key(pname) references patient(pname)
       );
       
DROP TABLE IF EXISTS caretaker;
CREATE TABLE caretaker (
       username	   varchar(25),
       password	   varchar(50),
       cname	   varchar(50),
       title	   varchar(10),
       email	   varchar(45),
       primary key (username)
       );


       
