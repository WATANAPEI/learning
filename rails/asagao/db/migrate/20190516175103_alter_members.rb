class AlterMembers < ActiveRecord::Migration[5.2]
  def change
    rename_column :members, :numner, :number
  end
end
