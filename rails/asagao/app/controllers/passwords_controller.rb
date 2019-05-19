class PasswordsController < ApplicationController
  before_action :login_required

  def show
    redirect_to :account
  end
  def edit
    @member = current_member 
  end
  def update
    @member = current_member
    current_password = params[:account][:current_member]

    if current_password.present?
      if @member.authenticate(current_password)
        @member.assign_attributes(params[:account])
        if @member.save
          redirect_to :account, notice: "change password"
        else
          render "edit"
        end
      else
        @member.errors.add(:current_password, :wrong)
        render "edit"
      end
    else
      @member.errors.add(:current_password, :empty)
      render "edit"
    end
  end
end
