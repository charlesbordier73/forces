DROP TABLE IF EXISTS AssistingUnits;
DROP TABLE IF EXISTS ConcernedInfractions;
DROP TABLE IF EXISTS ConcernedArticles;
DROP TABLE IF EXISTS Feedback;
DROP TABLE IF EXISTS Requisition;
DROP TABLE IF EXISTS Unit;
DROP TABLE IF EXISTS Infraction;
DROP TABLE IF EXISTS Article;



CREATE TABLE Unit(
    id SERIAL NOT NULL,
    unitName VARCHAR(100) NOT NULL,
    sigle VARCHAR(10),
    mail VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE Infraction(
    id SERIAL NOT NULL,
    label TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Article(
    id SERIAL NOT NULL,
    content TEXT NOT NULL,
    code VARCHAR(10) NOT NULL,
    alinea INT,
    PRIMARY KEY (id)
);

CREATE TABLE Requisition(
    id SERIAL NOT NULL,
    startDate TIMESTAMP NOT NULL,
    endDate TIMESTAMP NOT NULL,
    map TEXT NOT NULL,
    motivation TEXT NOT NULL,
    requestingUnit INT NOT NULL,
    approved BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (requestingUnit) REFERENCES Unit(id)
);


CREATE TABLE Feedback(
    id SERIAL NOT NULL,
    requisitionID INT NOT NULL,
    nbOfPersonControlled INT NOT NULL,
    nbOfInfractions INT NOT NULL,
    nbOfWeaponInfractions INT NOT NULL,
    nbOfRoadInfractions INT NOT NULL,
    nbOfPenalInfractions INT NOT NULL,
    nbOfOffences INT NOT NULL,
    nbOfFinesFifthClass INT NOT NULL,
    nbOfFinesOtherClass INT NOT NULL,
    nbOfVehiculesControlled INT NOT NULL,
    nbOfImmobilisations INT NOT NULL,
    nbOfReports INT NOT NULL,
    nbOfPersonsListened INT NOT NULL,
    nbOfCustody INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (requisitionID) REFERENCES Requisition(id)
);

CREATE TABLE AssistingUnits(
    unitID INT NOT NULL,
    requisitionsID INT NOT NULL,
    FOREIGN KEY (unitID) REFERENCES Unit(id),
    FOREIGN KEY (requisitionsID) REFERENCES Requisition(id)
);

CREATE TABLE ConcernedArticles(
    articleID INT NOT NULL,
    requisitionsID INT NOT NULL,
    FOREIGN KEY (articleID) REFERENCES Article(id),
    FOREIGN KEY (requisitionsID) REFERENCES Requisition(id)
);

CREATE TABLE ConcernedInfractions(
    infractionID INT NOT NULL,
    requisitionsID INT NOT NULL,
    FOREIGN KEY (infractionID) REFERENCES Infraction(id),
    FOREIGN KEY (requisitionsID) REFERENCES Requisition(id)
);

Insert into Infraction(label) values ('Armes');
Insert into Infraction (label) values ('Vols');
Insert into Infraction (label) values ('Drogues');
Insert into Infraction (label) values ('Terrorisme');