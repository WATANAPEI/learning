class MembersController < ApplicationController
  # member list
  def index
    @members = Member.order("number")
  end
  def show
    @member = Member.find(params[:id])
  end
  def new
    @member = Member.new(birthday: Date.new(1980, 1,1 ))
  end
  def edit
    @member = Member.find(params[:id])
  end
  def create
    @member = Member.new(params[:member])
    if @member.save
      redirect_to @member, notice: "registered a member"
    else
      render "new"
    end
  end
  def update
    @member = Member.find(params[:id])
    @member.assign_attributes(params[:member])
    if @member.save
      redirect_to @member, notice: "updated member information"
    else
      render "edit"
    end
  end
  def destroy
    @member = Member.find(params[:id])
    @member.destroy
    redirect_to :members, notice: "deleted a member"
  end
  def search
    @members = Member.search(params[:q])
    render "index"
  end
end
