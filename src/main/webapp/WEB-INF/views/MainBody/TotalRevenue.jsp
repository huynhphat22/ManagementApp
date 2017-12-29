<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- main content start-->
<div id="page-wrapper" ng-controller="TotalRevenueController as fctrl" ng-init="init()">
	<div class="main-page">
		<h3 class="title1">Report
				</h3>
				
		<div class="form-three widget-shadow">
			<form class="form-horizontal">
				<div class="form-group">
						<label for="selector1" class="col-sm-2 control-label">Dropdown Select</label>
						<div class="col-sm-8"><select ng-model="periodType" class="form-control1">
							<option value="0">Date </option>
							<option value="1">Week </option>
							<option value="2">Month </option>
							<option value="3">Quarter </option>
							<option value="4">Year </option>
						</select></div>
				</div>
				
				
				<div ng-switch="periodType">
					<div class="form-group" ng-switch-when="0">
						<label for="dateInput" class="col-sm-2 control-label">Date </label>
						<div class="col-sm-8">
							<input type="date" class="form-control1"  ng-model="date" id="dateInput">
						</div>
					</div>
					
					<div class="form-group" ng-switch-when="1">
						<label for="weekInput" class="col-sm-2 control-label">Week </label>
						<div class="col-sm-8">
							<input type="week" class="form-control1"  ng-model="week" id="weekInput">
						</div>
					</div>
					
					<div class="form-group" ng-switch-when="2">
						<label for="monthInput" class="col-sm-2 control-label">Month </label>
						<div class="col-sm-8">
							<input type="month" class="form-control1"  ng-model="month" id="monthInput">
						</div>
					</div>
					
					<div class="form-group" ng-switch-when="3">
						<label for="quarterInput" class="col-sm-2 control-label">Quarter</label>
						<input type="week" class="form-control1"  ng-model="quarter" id="quarterInput">
					</div>
					
					<div class="form-group" ng-switch-when="4">
						<label for="yearInput" class="col-sm-2 control-label">Quarter</label>
						<input type="number" class="form-control1"  ng-model="year" id="yearInput" min="1900" max="2100">
					</div>
				</div>
				
				<!--
				<div class="form-group">
					<label for="disabledinput" class="col-sm-2 control-label">Disabled Input</label>
					<div class="col-sm-8">
						<input disabled="" type="text" class="form-control1" id="disabledinput" placeholder="Disabled Input">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-8">
						<input type="password" class="form-control1" id="inputPassword" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<label for="checkbox" class="col-sm-2 control-label">Checkbox</label>
					<div class="col-sm-8">
						<div class="checkbox-inline1"><label><input type="checkbox"> Unchecked</label></div>
						<div class="checkbox-inline1"><label><input type="checkbox" checked=""> Checked</label></div>
						<div class="checkbox-inline1"><label><input type="checkbox" disabled=""> Disabled Unchecked</label></div>
						<div class="checkbox-inline1"><label><input type="checkbox" disabled="" checked=""> Disabled Checked</label></div>
					</div>
				</div>
				<div class="form-group">
					<label for="checkbox" class="col-sm-2 control-label">Checkbox Inline</label>
					<div class="col-sm-8">
						<div class="checkbox-inline"><label><input type="checkbox"> Unchecked</label></div>
						<div class="checkbox-inline"><label><input type="checkbox" checked=""> Checked</label></div>
						<div class="checkbox-inline"><label><input type="checkbox" disabled=""> Disabled Unchecked</label></div>
						<div class="checkbox-inline"><label><input type="checkbox" disabled="" checked=""> Disabled Checked</label></div>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label">Multiple Select</label>
					<div class="col-sm-8">
						<select multiple="" class="form-control1">
							<option>Option 1</option>
							<option>Option 2</option>
							<option>Option 3</option>
							<option>Option 4</option>
							<option>Option 5</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="txtarea1" class="col-sm-2 control-label">Textarea</label>
					<div class="col-sm-8"><textarea name="txtarea1" id="txtarea1" cols="50" rows="4" class="form-control1"></textarea></div>
				</div>
				<div class="form-group">
					<label for="radio" class="col-sm-2 control-label">Radio</label>
					<div class="col-sm-8">
						<div class="radio block"><label><input type="radio"> Unchecked</label></div>
						<div class="radio block"><label><input type="radio" checked=""> Checked</label></div>
						<div class="radio block"><label><input type="radio" disabled=""> Disabled Unchecked</label></div>
						<div class="radio block"><label><input type="radio" disabled="" checked=""> Disabled Checked</label></div>
					</div>
				</div>
				<div class="form-group">
					<label for="radio" class="col-sm-2 control-label">Radio Inline</label>
					<div class="col-sm-8">
						<div class="radio-inline"><label><input type="radio"> Unchecked</label></div>
						<div class="radio-inline"><label><input type="radio" checked=""> Checked</label></div>
						<div class="radio-inline"><label><input type="radio" disabled=""> Disabled Unchecked</label></div>
						<div class="radio-inline"><label><input type="radio" disabled="" checked=""> Disabled Checked</label></div>
					</div>
				</div>
				<div class="form-group">
					<label for="smallinput" class="col-sm-2 control-label label-input-sm">Small Input</label>
					<div class="col-sm-8">
						<input type="text" class="form-control1 input-sm" id="smallinput" placeholder="Small Input">
					</div>
				</div>
				<div class="form-group">
					<label for="mediuminput" class="col-sm-2 control-label">Medium Input</label>
					<div class="col-sm-8">
						<input type="text" class="form-control1" id="mediuminput" placeholder="Medium Input">
					</div>
				</div>
				<div class="form-group mb-n">
					<label for="largeinput" class="col-sm-2 control-label label-input-lg">Large Input</label>
					<div class="col-sm-8">
						<input type="text" class="form-control1 input-lg" id="largeinput" placeholder="Large Input">
					</div>
				</div>
				  -->
				
			</form>
		</div>
	</div>
</div>