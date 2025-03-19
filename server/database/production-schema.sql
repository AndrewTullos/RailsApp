drop database if exists solar;
create database solar;
use solar;

create table `user` (
	user_id int primary key auto_increment,
    username varchar(255) unique,
    `password` text
);

create table panel (
	panel_id int primary key auto_increment,
    section varchar(50) not null,
    `row` int not null,
    `column` int not null,
    year_installed int not null,
    material varchar(50) not null,
    is_tracking boolean not null,
    user_id int not null,
    foreign key (user_id) references `user`(user_id),
    constraint uq_section_row_column
		unique(section, `row`, `column`)
);

insert into `user` (username, `password`)
	values
    ('test username 1', 'password1'),
	('test username 2', 'password2');

insert into panel (section, `row`, `column`, year_installed, material, is_tracking, user_id)
		values
        ('The Ridge', 1, 1, 2020, 'POLY_SI', true, 1),
        ('The Ridge', 1, 2, 2020, 'CD_TE', true, 2),
        ('The Ridge', 1, 3, 2020, 'MONO_SI', true, 2),
        ('Flats', 4, 1, 2020, 'A_SI', false, 1),
        ('Flats', 5, 1, 2020, 'MONO_SI', false, 2);
