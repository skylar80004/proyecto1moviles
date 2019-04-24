class Users < ActiveRecord::Migration[5.2]
  
  def self.up
    create_table :users do |t|
      t.column :name, :string, null: false
      t.column :last_name, :string, null: false
      t.column :username, :string, null: false
      t.column :password, :string, null: false
      t.column :email, :string, null: false
    end
  end

  def self.down
    drop_table :users
  end

end
