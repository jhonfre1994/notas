
import { NgModule } from '@angular/core';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CardModule } from 'primeng/card';
import { AppRoutingModule } from './app.routing.module';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {MessagesModule} from 'primeng/messages';
import {MessageModule} from 'primeng/message';
import { SidebarModule } from 'primeng/sidebar';
import { PanelMenuModule } from 'primeng/panelmenu';
import { ChartModule } from 'primeng/chart';
import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';
import { TableModule } from 'primeng/table';
import { InputSwitchModule } from 'primeng/inputswitch';
import { ColorPickerModule } from 'primeng/colorpicker';
import { DataViewModule } from 'primeng/dataview';
import { DropdownModule } from 'primeng/dropdown';
import { PaginatorModule } from 'primeng/paginator'; // Here
import { PanelModule } from 'primeng/panel';
import { DialogModule } from 'primeng/dialog';
import { TreeModule } from 'primeng/tree';
import { PickListModule } from 'primeng/picklist';
import { ProgressBarModule } from 'primeng/progressbar';
import { TabViewModule } from 'primeng/tabview';
import { FieldsetModule } from 'primeng/fieldset';
import {MultiSelectModule} from 'primeng/multiselect';
import {ToastModule} from 'primeng/toast';

@NgModule({
    imports: [
        AppRoutingModule,
        ButtonModule,
        SidebarModule,
        PanelMenuModule,
        CardModule,
        ChartModule,
        ToolbarModule,
        SplitButtonModule,
        TableModule,
        InputSwitchModule,
        ColorPickerModule,
        DataViewModule,
        DropdownModule,
        PaginatorModule,
        PanelModule,
        DialogModule,
        TreeModule,
        PickListModule,
        InputTextModule,
        MessagesModule,
        MessageModule,
        ProgressBarModule,
        TabViewModule,
        FieldsetModule,
        MultiSelectModule,
        ToastModule,
        ConfirmDialogModule,
        InputTextareaModule
    ],
    exports: [
        AppRoutingModule,
        ButtonModule,
        SidebarModule,
        PanelMenuModule,
        CardModule,
        ChartModule,
        ToolbarModule,
        SplitButtonModule,
        TableModule,
        InputSwitchModule,
        ColorPickerModule,
        DataViewModule,
        DropdownModule,
        PaginatorModule,
        PanelModule,
        DialogModule,
        TreeModule,
        PickListModule,
        InputTextModule,
        MessagesModule,
        MessageModule,
        ProgressBarModule,
        TabViewModule,
        FieldsetModule,
        MultiSelectModule,
        ToastModule,
        ConfirmDialogModule,
        InputTextareaModule
    ]
})
export class MaterialModule { }