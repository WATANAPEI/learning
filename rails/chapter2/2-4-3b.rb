class Robot
  attr_reader :name
  attr_accessor :score

  def initialize(name)
    @name = name
    @x = @y = 0
    @score = 10
  end

  def move(x, y)
    @x += x
    @y += y
  end

  def to_s
    "#{@name}: #{@x}, #{@y}"
  end
end

robo1 = Robot.new("ロボ一号")
robo2 = Robot.new("ロボ二号")
puts robo1
puts "score: #{robo2.score}"
robo2.score = 90
puts "score: #{robo2.score}"
