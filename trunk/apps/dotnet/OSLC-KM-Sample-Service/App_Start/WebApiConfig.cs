using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using OSLC4Net.Core.DotNetRdfProvider;
using OSLC4Net.Core.JsonProvider;

using System.Web;
using OSLC.Controllers;
using System.Web.Http;
using System.Web.Hosting;
namespace OSLC {
    public static class WebApiConfig {
        public static void Register(HttpConfiguration config) {
            //config.Routes.MapHttpRoute(
            //       name: "ApiById",
            //       routeTemplate: "oslc/{controller}/{id}",
            //       defaults: new { id = RouteParameter.Optional },
            //       constraints: new { id = @"^[0-9]+$" }
            //   );

            //config.Routes.MapHttpRoute(
            //    name: "ApiByName",
            //    routeTemplate: "oslc/{controller}/{action}/{name}",
            //    defaults: null,
            //    constraints: new { name = @"^[a-z]+$" }
            //);

            //config.Routes.MapHttpRoute(
            //    name: "ApiByAction",
            //    routeTemplate: "oslc/{controller}/{action}",
            //    defaults: new { action = "Get" }
            //);


            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "oslc/{controller}/{action}/{id}/",
                defaults: new { id = RouteParameter.Optional}
            );



            config.Formatters.Clear();
            config.Formatters.Add(new RdfXmlMediaTypeFormatter());
            //config.Formatters.Add(new OSLC.Models.formatters.JSONFormatter());

            System.Web.HttpContext context = HttpContext.Current;
            
            //string baseUrl = context.Request.Url.Scheme + "://" + 
            //    context.Request.Url.Authority + 
            //    context.Request.ApplicationPath.TrimEnd('/') + "/oslc";
            string baseUrl = "http://localhost:31880/oslc";
            string hackUrl = "http://localhost:31885/oslc";
            //Once per service provider
            ArtifactsServiceProviderController.init(baseUrl);

        }
    }
}
