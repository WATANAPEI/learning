class MembersController < ApplicationController
  before_action :login_required

  # member list
  def index
    @members = Member.order("number")
      .page(params[:page]).per(15)
  end
  def show
    @member = Member.find(params[:id])
  end
end
