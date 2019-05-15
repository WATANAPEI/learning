print "input price: "
num = gets.to_i
if num >= 1500
  puts "free to ship"
elsif 0 < num && num < 1500
  puts "ship charge is 300"
else
  puts "wrong input"
end

