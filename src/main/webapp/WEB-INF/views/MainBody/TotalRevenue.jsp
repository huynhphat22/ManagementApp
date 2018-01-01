<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main content start-->


<div id="page-wrapper" ng-controller="TotalRevenueController as ctrl" ng-init="init()">
	<div class="main-page">
		<h3 class="title1">Condition
				</h3>
				
		<div class="form-three widget-shadow">
			<form class="form-horizontal">
				<div class="form-group">
						<label for="selector1" class="col-sm-2 control-label">Period type</label>
						<div class="col-sm-8"><select ng-model="periodType" class="form-control1" ng-change="onPeriodTypeChanged()">
							<option value="0">Date </option>
							<option value="1">Week </option>
							<option value="2">Month </option>
							<option value="3">Quarter </option>
							<option value="4">Year </option>
						</select>
						</div>
				</div>
				
				
				<div ng-switch="periodType">
					<div class="form-group" ng-switch-when="0">
						<label for="dateInput" class="col-sm-2 control-label">Date </label>
						<div class="col-sm-8">
							<input type="date" class="form-control1 required"  ng-model="data.date" id="dateInput">
						</div>
					</div>
					
					<div class="form-group" ng-switch-when="1">
						<label for="weekInput" class="col-sm-2 control-label">Week </label>
						<div class="col-sm-8">
							<input type="week" class="form-control1 required"  ng-model="data.date" id="weekInput">
						</div>
					</div>
					
					<div class="form-group" ng-switch-when="2">
						<label for="monthInput" class="col-sm-2 control-label">Month </label>
						<div class="col-sm-8">
							<input type="month" class="form-control1 required"  ng-model="data.date" id="monthInput">
						</div>
					</div>
					
					<div class="form-group" ng-switch-when="3">
						<label for="quarterInput" class="col-sm-2 control-label">Quarter</label>
						<div class="col-sm-8">
							<input type="number" class="form-control1"  ng-model="data.quarter" id="quarterInput" min="1" max="4">
						</div>
					</div>
					
					<div class="form-group" ng-switch-when="4">
						<label for="yearInput" class="col-sm-2 control-label">Year</label>
						<div class="col-sm-8">
							<input type="number" class="form-control1"  ng-model="data.year" id="yearInput" min="1900" max="2100">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<button class="btn btn-default" style="float: right;" ng-click="onGetReportClick()">Get report</button>
					
				</div>
				
			</form>
		</div>
		
		<div class="clearfix"></div>
		
		<div class="alert alert-warning" ng-show="data.error != null">
				{{data.error}}
			</div>
		
		<div class="tables" ng-if="data.hasData">
			<h3 class="title1">Report
			</h3>

			<div class="panel panel-primary" >
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-7">
							<div class="form-inline">
								<div class="form-group">
									<button class="btn btn-default btn-sm" ng-disabled="ctrl.page === 1" ng-click="ctrl.decreasePage();" disabled="disabled">
										<i class="glyphicon glyphicon-menu-left"></i>
									</button>
								</div>

								<div class="form-group">
									<select ng-change="ctrl.changePage()" ng-options="n for n in ctrl.range(1, ctrl.listFoods.totalPages, 1)" ng-model="ctrl.page" class="form-control input-sm ng-pristine ng-untouched ng-valid ng-not-empty"><option label="1" value="number:1" selected="selected">1</option></select>
								</div>


								<button class="btn btn-default btn-sm ng-binding">1/</button>


								<button class="btn btn-default btn-sm" ng-disabled="ctrl.page === ctrl.listFoods.totalPages" ng-click="ctrl.increasePage(ctrl.listFoods)">
									<i class="glyphicon glyphicon-menu-right"></i>
								</button>

								<div class="form-group">
									<label>Records per page : </label>
									<select ng-change="ctrl.changeRecordsPerPage()" ng-options="n for n in ctrl.range(1, 20, 1)" ng-model="ctrl.size" class="form-control input-sm ng-pristine ng-untouched ng-valid ng-not-empty"><option label="1" value="number:1">1</option><option label="2" value="number:2">2</option><option label="3" value="number:3">3</option><option label="4" value="number:4">4</option><option label="5" value="number:5">5</option><option label="6" value="number:6">6</option><option label="7" value="number:7">7</option><option label="8" value="number:8">8</option><option label="9" value="number:9">9</option><option label="10" value="number:10" selected="selected">10</option><option label="11" value="number:11">11</option><option label="12" value="number:12">12</option><option label="13" value="number:13">13</option><option label="14" value="number:14">14</option><option label="15" value="number:15">15</option><option label="16" value="number:16">16</option><option label="17" value="number:17">17</option><option label="18" value="number:18">18</option><option label="19" value="number:19">19</option><option label="20" value="number:20">20</option></select>
								</div>

							</div>
						</div>
						
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-responsive">
						<thead>
							<tr>
								
								<th ng-switch="periodType">
									<span ng-switch-when="0">Hour</span>
									<span ng-switch-when="1">Weekday</span>
									<span ng-switch-when="2">Day</span>
									<span ng-switch-when="3">Month</span>
									<span ng-switch-when="4">Month</span>
								</th>
								<th>Revenue</th>
							</tr>
						</thead>
						<tbody>
							<!-- ngRepeat: f in ctrl.listFoods.content -->
							<tr ng-repeat="row in data.dataList track by $index">
								<td>{{row.time != 0 ? row.time : row.dayName}}</td>
								<td>{{row.revenue}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>