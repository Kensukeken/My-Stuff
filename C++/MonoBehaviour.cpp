using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class pathAI : MonoBehaviour
{
   public List<Vector2> positions = new List<Vector2>();

    public int index = 0;

    public GameObject player;
    private void OnDrawGizmos()
    {
        // Draw the patrol points
        foreach (Vector2 position in positions)
        {
            Gizmos.color = Color.blue;
            Gizmos.DrawSphere(position, .2f);
        }

        // Draw the linecast
        Vector2 direction = player.transform.position - this.transform.position;
        float distance = Vector2.Distance(this.transform.position, player.transform.position);
        RaycastHit2D hit = Physics2D.Linecast(this.transform.position, player.transform.position);
        if (hit.collider != null)
        {
            if (!hit.collider.CompareTag("walls") && hit.collider.CompareTag("Player"))
            {
                Gizmos.color = Color.green;
                Gizmos.DrawLine(this.transform.position, hit.point);
            }
            else
            {
                Gizmos.color = Color.red;
                Gizmos.DrawLine(this.transform.position, player.transform.position);
            }
        }
    }

   
   
    void Update()
    {
        
        RaycastHit2D hit = Physics2D.Linecast(this.transform.position, player.transform.position);
        if (!hit.collider.CompareTag("Player"))
        {
            patrol();
        }
        else 
        {

            this.transform.position = Vector2.MoveTowards(this.transform.position, player.transform.position, 0.02f);

        }
    }
}
