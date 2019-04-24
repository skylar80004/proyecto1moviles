class ScheduleController < ApplicationController

    def list
        @schedules = Schedule.all
    end
    
    def show
        @schedule = Schedule.find(params[:id])
    end
    
    def new
        @schedule = Schedule.new
        @restaurants = Restaurant.all
    end
    
    def create
       @schedule = Schedule.new(schedules_params)
       if @schedule.save
        redirect_to :action => 'list'
       else
        @restaurants = Restaurant.all
        render :action => 'new'
       end
    end

    def schedules_params
        params.require(:schedules).permit(:restaurant_id, :day, :start, :end)
    end
        
    def edit
        @schedule = Schedule.find(params[:id])
        @restaurants = Restaurant.all
    end
    
    def update
        @schedule = Schedule.find(params[:id])
        if @schedule.update_attributes(schedule_params)
            redirect_to :action => 'show', :id => @schedule
        else
            @restaurants = Restaurant.all
            render :action => 'edit'
        end
    end

    def schedule_params
        params.require(:schedule).permit(:restaurant_id, :day, :start, :end)
    end
    
    def delete
        Schedule.find(params[:id]).destroy
        redirect_to :action => 'list'
    end
        
    private
        

end
