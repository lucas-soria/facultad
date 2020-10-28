from django.views import View
from .models import Company
from django.http import JsonResponse
from django.forms import model_to_dict


# Create your views here.
class CompanyListView(View):
    def get(self, request):
        lista = Company.objects.all()
        return JsonResponse(list(lista.values()), safe=False)


class CompanyDetailView(View):
    def get(self, request, pk):
        lista = Company.objects.get(pk=pk)
        return JsonResponse(model_to_dict(lista))
