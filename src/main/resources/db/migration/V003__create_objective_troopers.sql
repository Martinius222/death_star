create table if not exists operationtroopers(
    TrooperID int,
    FOREIGN KEY (TrooperID) REFERENCES Troopers (ID),
    ObjectiveID int,
    FOREIGN KEY (ObjectiveID) REFERENCES Objectives (ID),
    PRIMARY KEY (TrooperID, ObjectiveID)
)