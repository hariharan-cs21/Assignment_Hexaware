create table Book(
	id int auto_increment primary key,
    title varchar(255) not null,
    price double not null,
    author varchar(255) not null,
    publication_house enum('McGraw-Hill', 'Dreamfolks', 'Warner Bros') not null,
    category enum('Fiction','War','Comedy','Sports') not null,
    book_count int not null,
    status enum('in_stock','out_of_stock') not null
);
INSERT INTO Book (title, price, author, publication_house, category, book_count, status)
VALUES 
('The Art of War', 399, 'Sun Tzu', 'McGraw-Hill', 'War', 10, 'in_stock'),
('Laugh Out Loud', 599, 'Jane Doe', 'Dreamfolks', 'Comedy', 5, 'in_stock'),
('Champions of the Field', 1399, 'John Smith', 'Warner Bros', 'Sports', 0, 'out_of_stock'),
('Fictional Realms', 2199, 'Alice Wonderland', 'Dreamfolks', 'Fiction', 3, 'in_stock'),
('Battlefield Stories', 499, 'Tom Hardy', 'McGraw-Hill', 'War', 0, 'out_of_stock');

-- 1.fetch all books that are "IN STOCK" and price is less than give value.
delimiter &&
create procedure proc_fetch_all_books(in par_price int)
begin
	select * from book where status='in_stock' and price<par_price;
end;
call proc_fetch_all_books(400)

-- 2 Delete books that are from given publication_house
delimiter &&
create procedure proc_delete_from_publication(in par_publication varchar(255))
begin
	delete from book where publication_house=par_publication;
end;
call proc_delete_from_publication("Dreamfolks")

-- 3. update the price of books by given percent based on given category.
delimiter &&
create procedure proc_update_price(in par_percent int,in par_category varchar(200))

begin
	update book
    set price=price+(price*par_percent/100)
    where category=par_category;
end;
call proc_update_price(5,'War')
select * from book
