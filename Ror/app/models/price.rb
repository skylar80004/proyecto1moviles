class Price < ApplicationRecord
    validates_presence_of  :description
    has_many :restaurants
end
