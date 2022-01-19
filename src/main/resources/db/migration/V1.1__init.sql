
CREATE TABLE categories
(
    id serial,
    name_category varchar NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name_category)
);

CREATE TABLE persons
(
    id serial,
    name varchar NOT NULL,
    prename varchar NOT NULL,
    email varchar NOT NULL,
    password varchar NOT NULL,
    phone varchar NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (email, phone)
);


CREATE TABLE services
(
    id serial,
    name_service varchar NOT NULL,
    description varchar,
    categoryId int,
    PRIMARY KEY(id),
    UNIQUE (name_service),
    FOREIGN KEY (categoryId) REFERENCES categories(id)
);

CREATE TABLE offers
(
    personId int,
    serviceId int,
    isValid boolean NOT NULL DEFAULT TRUE,
    PRIMARY KEY(personId, serviceId),
    FOREIGN KEY (personId) REFERENCES persons(id),
    FOREIGN KEY (serviceId) REFERENCES services(id)
)