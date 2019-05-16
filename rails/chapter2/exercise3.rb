class Book
  attr_reader :title
  attr_reader :author
  attr_reader :price
  def initialize(title, author, price)
    @title = title
    @author = author
    @price = price
  end
end

book1 = Book.new("higanbana", "soseki natsume", 540)
puts "#{book1.title}, #{book1.author}著, #{book1.price}円"
