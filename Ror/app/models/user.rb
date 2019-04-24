class User < ApplicationRecord
    validates :name, :presence => true
    validates :last_name, :presence => true
    validates :username, :uniqueness => true
    validates :password, :length => { :in => 6..20 }
    validates :email, :uniqueness => true
end
