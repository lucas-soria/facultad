from django.urls import path
from .views import CompanyListView, CompanyDetailView


urlpatterns = [
    path('company/', CompanyListView.as_view(), name='company_list'),
    path('company/<int:pk>/', CompanyDetailView.as_view(), name='company'),
]
