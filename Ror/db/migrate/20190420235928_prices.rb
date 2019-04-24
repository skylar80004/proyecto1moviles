class Prices < ActiveRecord::Migration[5.2]
  
  def self.up
    create_table :prices do |t|
      t.column :description, :string, null: false
    end

    Price.create :description => 'Caro'
    Price.create :description => 'Medio'
    Price.create :description => 'Barato'

  end

  def self.down
    drop_table :prices
  end

end
