class Comments < ActiveRecord::Migration[5.2]
 
  def self.up
    create_table :comments do |t|
      t.column :comment, :text, null: false
      t.column :restaurant_id, :integer, null: false
    end
  end

  def self.down
    drop_table :comments
  end
  
end
