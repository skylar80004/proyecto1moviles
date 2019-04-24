class Restaurant < ApplicationRecord
    validates :name, :presence => true
    validates :stars, :presence => true
    validates :kind_food, :presence => true
    belongs_to :price
    has_many :comments
    has_many :schedules
    has_many :photos
end
