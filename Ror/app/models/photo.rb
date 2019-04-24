class Photo < ApplicationRecord
    validates :description, :presence => true
    belongs_to :restaurant
    has_one_attached :image
end
