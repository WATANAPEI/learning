def triangle(params)
  params[:base] * params[:height] / 2.0
end
area = triangle(base: 2.3, height: 3.4)
puts "area: #{area}"
