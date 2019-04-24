class Restaurants < ActiveRecord::Migration[5.2]

  def self.up
    create_table :restaurants do |t|
      t.column :name, :string, null: false
      t.column :stars, :float
      t.column :latitude, :float
      t.column :longitude, :float
      t.column :kind_food, :string, null: false
      t.column :price_id, :integer, null: false
      t.column :phone, :string, null: false
      t.column :website, :string, null: false
    end
  end

  def self.down
    drop_table :restaurants
  end

end
