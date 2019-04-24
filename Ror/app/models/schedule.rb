class Schedule < ApplicationRecord
    validates :day, :presence => true
    validates :start, :presence => true
    validates :end, :presence => true
    belongs_to :restaurant
end
