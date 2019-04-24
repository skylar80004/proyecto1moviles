class Schedules < ActiveRecord::Migration[5.2]
  
  def self.up
    create_table :schedules do |t|
      t.column :day, :string, null: false
      t.column :start, :time, null: false
      t.column :end, :time, null: false
      t.column :restaurant_id, :integer, null: false
    end
  end

  def self.down
    drop_table :schedule
  end
  
end
