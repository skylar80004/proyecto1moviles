class CommentController < ApplicationController

    def list
        @comments = Comment.all
    end
    
    def show
        @comment = Comment.find(params[:id])
    end
    
    def new
        @comment = Comment.new
        @restaurants = Restaurant.all
    end
    
    def create
       @comment = Comment.new(comment_params)
       if @comment.save
        redirect_to :action => 'list'
       else
        @restaurants = Restaurant.all
        render :action => 'new'
       end
    end
    
    def delete
        Comment.find(params[:id]).destroy
        redirect_to :action => 'list'
    end

    private
        def comment_params
            params.require(:comments).permit(:restaurant_id, :comment)
        end

end
