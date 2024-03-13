use centennial;
CREATE TABLE passenger(
    passengerId SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userName varchar(50) NOT NULL,
    password varchar(30) NOT NULL,
    firstname varchar(20) NOT NULL,
    lastname varchar(20) NOT NULL,
    address varchar(50) NOT NULL,
    city varchar(20) NOT NULL,
    postalCode varchar(10) NOT NULL,
    country varchar(10) NOT NULL
);

use centennial;
CREATE TABLE cruise(
    cruiseId SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cruiseName varchar(20) NOT NULL,
    shipName varchar(20) NOT NULL,
    startDate date NOT NULL,
    endDate date NOT NULL,
    destination varchar(20) NOT NULL
);

use centennial;
CREATE TABLE booking(
    reservationId SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    passengerId SMALLINT NOT NULL,
    cruiseId SMALLINT NOT NULL,
    stateroomType varchar(20) NOT NULL,
    totalGuest SMALLINT NOT NULL,
    totalPrice DOUBLE NOT NULL,
    FOREIGN KEY (passengerId) REFERENCES passenger(passengerId),
    FOREIGN KEY (cruiseId) REFERENCES cruise(cruiseId)
);

INSERT INTO centennial.cruise (cruiseName, shipName, startDate, endDate, destination)
VALUES ('Western Caribbean', 'Star of the Seas', '2024-02-25', '2024-03-01', 'Puerto Costa Maya');

INSERT INTO centennial.cruise (cruiseName, shipName, startDate, endDate, destination)
VALUES ('Spain', 'Explorer of the Seas', '2024-03-15', '2024-03-25', 'Barcelona');

INSERT INTO centennial.cruise (cruiseName, shipName, startDate, endDate, destination)
VALUES ('Alaska Adventure', 'Quantum of the Seas', '2024-05-27', '2024-06-23', 'Sitka');
