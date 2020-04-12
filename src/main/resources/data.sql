create table book (
    id int auto_increment primary key ,
    name varchar(100) not null ,
    page int not null
);

insert into book (name, page) VALUES
                                     ( 'Yuzyıllık Yalnızlık', 250 ),
                                     ( 'Corona günlerinde aşk', 200 );
