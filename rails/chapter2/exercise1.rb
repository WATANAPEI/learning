print "input price: "
price = gets.chomp
price = ((price.to_i)*1.08).to_i
puts "#{price} with tax"
