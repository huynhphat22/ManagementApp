
<!-- main content start-->
<div id="page-wrapper" ng-controller="Profile as pctrl">
	<div class="main-page">
		<div class="tables">
			<h3 class="title1">
				Profile
			</h3>

			<div class="alert alert-success" ng-cloak
				ng-show="pctrl.successMessage">
				<strong>Success!</strong>
				<p ng-bind="pctrl.successMessage"></p>
			</div>

			<div class="alert alert-danger" ng-cloak ng-show="pctrl.errorMessage">
				<strong>Error!</strong>
				<p ng-bind="pctrl.errorMessage"></p>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading">
					<strong ng-cloak>Change Password</strong>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-6">
							<form ng-submit="pctrl.submit()" name="changePasswordForm">
								<div class="form-group">
									<label>UserName</label> <input required type="text"
										ng-model="pctrl.passwordChange.username" class="form-control" />
								</div>
								<div class="form-group">
									<label>Current Password</label> <input required type="password"
										ng-model="pctrl.passwordChange.password" class="form-control" />
								</div>
								<div class="form-group">
									<label>New Password</label> <input required type="password"
										ng-model="pctrl.passwordChange.newPassword"
										class="form-control" />
								</div>
								<div class="form-group">
									<label>Confirm New Password</label> <input required type="password"
										ng-model="pctrl.passwordChange.reNewPassword"
										class="form-control" />
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-info">Change Password</button>
								</div>
								<div class="row"></div>
							</form>
						</div>

						<div class="col-sm-6">

							<div class="form-group">
								<label>Staff Name</label> <input disabled
								value="${user.staffName }" class="form-control" />
							</div>
							<div class="form-group">
								<label>Address</label> <input disabled
									value="${user.address }" class="form-control" />
							</div>
							<div class="form-group">
								<label>Phone Number</label> <input disabled
									value="${user.phoneNumber }" class="form-control" />
							</div>
							<div class="form-group">
								<label>Role</label> <input disabled value="${user.role }"
									class="form-control" />
							</div>
							<div class="form-group">
								<label>Department</label> <input disabled
									value="${user.departmentId }" class="form-control" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>