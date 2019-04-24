class PhotoController < ApplicationController

    def list
        @photos = Photo.all
    end
    
    def show
        @photo = Photo.find(params[:id])
    end
    
    def new
        @photo = Photo.new
        @restaurants = Restaurant.all
    end
    
    def create
       @photo = Photo.new(photo_params)
       if @photo.save
        redirect_to :action => 'list'
       else
        @restaurants = Restaurant.all
        render :action => 'new'
       end
    end
    
    def delete
        Photo.find(params[:id]).destroy
        redirect_to :action => 'list'
    end

    private
        def photo_params
            params.require(:photos).permit(:restaurant_id, :description, :image)
        end

end
