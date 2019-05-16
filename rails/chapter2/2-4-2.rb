class Robot
  def initialize(name)
    @name = name
    @x = @y = 0
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
puts robo2
robo2.move(2,3)
puts robo2

