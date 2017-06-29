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
    map TEXT,
    motivation TEXT NOT NULL,
    requestingUnit INT,
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

Insert into Infraction(label) values ('à la législation des armes et explosifs (art.222-54, 322-11-1 du code pénal et L. 317-8 du code de la sécurité intérieure, L. 2353-4 du code de la défense');
Insert into Infraction (label) values ('vols et recels (art. 311-1, 311-3, 311-4 et 321.1, 321-2, 321-3, 322-1 à 322-6 du code Pénal)');
Insert into Infraction (label) values ('Drogues');
Insert into Infraction (label) values ('Attendu que la menace terroriste est particulièrement élevée actuellement ; que les opérations de guerre dans la zone irako-syrienne conduisent les organisations terroristes à multiplier leurs menaces et leurs visées contre la France ; que la zone montpelliéraine au sens large est à cet égard particulièrement sensible, en raison de l''importance de la radicalisation qui y a été constatée et des symboles qu''elle pourrait représenter sur le plan social et culturel ; que l''attitude hostile des mouvements terroristes impose le plus haut niveau de prévention des risques ; qu''il est donc nécessaire, pour répondre à ce risque exceptionnellement présent de prolonger au-delà de 24 heures les réquisitions de contrôles déjà ordonnées, dans des lieux particulièrement sensibles à raison de leur fréquentation ou des axes de communication qu''ils constituent);