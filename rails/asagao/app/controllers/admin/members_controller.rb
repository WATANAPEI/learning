class Admin::MembersController < Admin::Base

  # member list
  def index
    @members = Member.order("number")
      .page(params[:page]).per(15)
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
    @member = Member.new(member_params)
    if @member.save
      redirect_to [:admin, @member], notice: "registered a member"
    else
      render "new"
    end
  end
  def update
    @member = Member.find(params[:id])
    @member.assign_attributes(member_params)
    if @member.save
      redirect_to [:admin, @member], notice: "updated member information"
    else
      render "edit"
    end
  end
  def destroy
    @member = Member.find(params[:id])
    @member.destroy
    redirect_to :admin_members,  notice: "deleted a member"
  end
  def search
    @members = Member.search(params[:q])
      .page(params[:page]).per(15)
    render "index"
  end
  private def member_params
    attrs = [
      :new_profile_picture,
      :remove_profile_picture, 
      :number,
      :name,
      :full_name,
      :sex,
      :birthday,
      :email,
      :administrator
    ]
    attrs << :password if params[:action] =="create"
    params.require(:member).permit(attrs)
  end
end
