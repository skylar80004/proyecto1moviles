class RestaurantController < ApplicationController
    
    def list
        @restaurants = Restaurant.all
    end
    
    def show
        @restaurant = Restaurant.find(params[:id])
    end
    
    def new
        @restaurant = Restaurant.new
        @prices = Price.all
    end
    
    def create
       @restaurant = Restaurant.new(restaurant_params)
       if @restaurant.save
        redirect_to :action => 'list'
       else
        @prices = Price.all
        render :action => 'new'
       end
    end
        
    def edit
        @restaurant = Restaurant.find(params[:id])
        @prices = Price.all
    end
    
    def update
        @restaurant = Restaurant.find(params[:id])
        if @restaurant.update_attributes(restaurant_params)
            redirect_to :action => 'show', :id => @restaurant
        else
            @prices = Price.all
            render :action => 'edit'
        end
    end
    
    def delete
        Restaurant.find(params[:id]).destroy
        redirect_to :action => 'list'
    end
        
    def restaurant_params
        params.require(:restaurants).permit(:name, :stars, :latitude, :longitude, :kind_food, :price_id, :phone, :website)
    end
end
