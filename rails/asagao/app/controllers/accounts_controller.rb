class AccountsController < ApplicationController
  before_action :login_required

  def show
    @member = current_member 
  end

  def edit
    @member = current_member
  end

  def update
    @member = current_member
    @member.assign_attributes(params[:account])
    if @member.save
      redirect_to :account, notice: "updated your account info"
    else
      render "edit"
    end
  end
end
