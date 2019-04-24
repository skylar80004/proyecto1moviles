class Photos < ActiveRecord::Migration[5.2]

  def self.up
    create_table :photos do |t|
      t.column :description, :text, null: false
      t.column :restaurant_id, :integer, null: false
    end
  end

  def self.down
    drop_table :photos
  end
  
end
